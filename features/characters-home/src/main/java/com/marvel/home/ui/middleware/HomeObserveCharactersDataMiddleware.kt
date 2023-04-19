package com.marvel.home.ui.middleware

import com.marvel.common.tea.SimpleMiddleware
import com.marvel.home.usecase.ObserveCharactersUpdateUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import com.marvel.home.ui.HomeCommand as Command
import com.marvel.home.ui.HomeEvent as Event

internal class HomeObserveCharactersDataMiddleware @Inject constructor(
    private val observeCharactersUpdateUseCase: ObserveCharactersUpdateUseCase
) : SimpleMiddleware<Command.ObserveCharactersData, Event.Internal> {

    override fun execute(command: Command.ObserveCharactersData): Observable<Event.Internal> =
        observeCharactersUpdateUseCase.execute(
            ObserveCharactersUpdateUseCase.Params(
                forceFetch = command.isRefresh
            )
        ).toObservable<Event.Internal>().map {
            Event.Internal.ObservingCharactersData(shouldRefresh = false)
        }
}
