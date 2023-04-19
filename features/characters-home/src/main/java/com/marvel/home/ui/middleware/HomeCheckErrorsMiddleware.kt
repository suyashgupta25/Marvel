package com.marvel.home.ui.middleware

import com.marvel.common.error.ErrorMessageMapper
import com.marvel.common.tea.SimpleMiddleware
import com.marvel.common.util.StringProvider
import com.marvel.home.repository.CharacterListRepository
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject
import com.marvel.home.ui.HomeCommand as Command
import com.marvel.home.ui.HomeEvent as Event

internal class HomeCheckErrorsMiddleware @Inject constructor(
    private val characterListRepository: CharacterListRepository,
    private val errorMessageMapper: ErrorMessageMapper,
    private val stringProvider: StringProvider
) : SimpleMiddleware<Command.ObserveCharacterErrors, Event.Internal> {

    override fun execute(command: Command.ObserveCharacterErrors): Observable<Event.Internal> =
        characterListRepository.error.map { errorData ->
            if (errorData.isMainError) {
                Event.Internal.InitialDataError(
                    toolbarTitle = stringProvider.getHomeScreenTitle(),
                    message = errorData.message
                )
            } else {
                Event.Internal.ShowConsecutiveDataError(errorData.message, true)
            }
        }.onErrorResumeNext {
            Timber.e(it)
            Observable.just(it.createMainErrorMessageEvent())
        }

    private fun Throwable.createMainErrorMessageEvent(): Event.Internal =
        Event.Internal.InitialDataError(
            toolbarTitle = stringProvider.getHomeScreenTitle(),
            message = errorMessageMapper.apply(this)
        )
}
