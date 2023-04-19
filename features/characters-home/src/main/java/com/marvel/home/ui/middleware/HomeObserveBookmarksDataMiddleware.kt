package com.marvel.home.ui.middleware

import com.marvel.common.tea.SimpleMiddleware
import com.marvel.home.usecase.ObserveBookmarksUpdateUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import com.marvel.home.ui.HomeCommand as Command
import com.marvel.home.ui.HomeEvent as Event

internal class HomeObserveBookmarksDataMiddleware @Inject constructor(
    private val observeBookmarksUpdateUseCase: ObserveBookmarksUpdateUseCase
) : SimpleMiddleware<Command.ObserveBookmarksData, Event.Internal> {

    override fun execute(command: Command.ObserveBookmarksData): Observable<Event.Internal> =
        observeBookmarksUpdateUseCase.execute(Unit).toObservable<Event.Internal>().map {
            Event.Internal.ObservingBookmarksData
        }
}
