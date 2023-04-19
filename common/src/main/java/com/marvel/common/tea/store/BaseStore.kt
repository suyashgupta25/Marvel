package com.marvel.common.tea.store

import com.marvel.common.tea.MiddlewareDelegate
import com.marvel.common.tea.StateReducer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

class BaseStore<Event : Any, State : Any, Command : Any>(
    initialState: State,
    private val reducer: StateReducer<Event, State, Command>,
    private val middlewareDelegate: MiddlewareDelegate<Command, out Event>
) : Store<Event, State> {

    private val disposables = CompositeDisposable()

    // Only to emit states to subscribers
    private val statesInternal = BehaviorSubject.createDefault(initialState)
    private val eventsInternal = PublishSubject.create<Event>()
    private val commandsInternal = PublishSubject.create<Command>()

    private val isStartedInternal = AtomicBoolean(false)

    override val states: Observable<State> = statesInternal.distinctUntilChanged()
    override val currentState: State get() = statesInternal.value!!

    override val isStarted
        get() = isStartedInternal.get()

    override fun accept(event: Event) = eventsInternal.onNext(event)

    override fun start(): Store<Event, State> {
        if (!isStartedInternal.compareAndSet(false, true)) {
            Timber.e("Store is already started. Usually, it happened inside StoreHolder.")
        }

        eventsInternal
            .observeOn(Schedulers.computation())
            .flatMap { event ->
                Timber.d("New event: $event")
                val state = statesInternal.value!!
                val result = reducer.reduce(event, state)
                statesInternal.onNext(result.state)

                Observable.fromIterable(result.commands)
            }.subscribe(commandsInternal::onNext) { error ->
                Timber.e(error, "You must handle all errors inside reducer")
            }.bind()

        commandsInternal
            .flatMap { command ->
                Timber.d("Executing command: $command")
                middlewareDelegate.processCommands(command)
                    .subscribeOn(Schedulers.io())
                    .doOnError(Timber::d)
                    .onErrorResumeNext { Observable.empty() }
            }
            .subscribe(eventsInternal::onNext) {
                Timber.e("Unexpected middleware error")
            }.bind()

        return this
    }

    private fun Disposable.bind() = let(disposables::add)

    override fun stop() {
        isStartedInternal.set(false)
        disposables.clear()
    }
}
