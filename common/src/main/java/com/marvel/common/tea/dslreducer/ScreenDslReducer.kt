package com.marvel.common.tea.dslreducer

import com.marvel.common.tea.StateReducer
import kotlin.reflect.KClass

/**
 * Splits a stream of [Event] in two - UI steam and Internal stream - pushing them separated to [ResultBuilder]
 */
@Suppress("UNCHECKED_CAST")
abstract class ScreenDslReducer<Event : Any, Ui : Any, Internal : Any, State : Any, Command : Any>(
    private val uiEventClass: KClass<Ui>,
    private val internalEventClass: KClass<Internal>
) : StateReducer<Event, State, Command> {

    protected inner class Result(state: State) : ResultBuilder<State, Command>(state)

    protected abstract fun Result.ui(event: Ui): Any?

    protected abstract fun Result.internal(event: Internal): Any?

    final override fun reduce(
        event: Event,
        state: State
    ): com.marvel.common.tea.dslreducer.Result<State, Command> {
        val body = Result(state)
        when {
            // splits a stream of [Event] in two - UI steam and Internal stream
            uiEventClass.java.isAssignableFrom(event.javaClass) -> body.ui(event as Ui)
            internalEventClass.java.isAssignableFrom(event.javaClass) -> body.internal(event as Internal)
            else -> error("Event ${event.javaClass} is neither UI nor Internal")
        }
        return body.build()
    }
}
