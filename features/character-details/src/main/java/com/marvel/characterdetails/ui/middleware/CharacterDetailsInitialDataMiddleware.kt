package com.marvel.characterdetails.ui.middleware

import com.marvel.characterdetails.repository.CharacterDetailsRepository
import com.marvel.characterdetails.ui.mapper.CharacterDetailsInitialDataMapper
import com.marvel.common.error.ErrorMessageMapper
import com.marvel.common.tea.SimpleMiddleware
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject
import com.marvel.characterdetails.ui.CharacterDetailsCommand as Command
import com.marvel.characterdetails.ui.CharacterDetailsEvent as Event

internal class CharacterDetailsInitialDataMiddleware @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository,
    private val characterDetailsInitialDataMapper: CharacterDetailsInitialDataMapper,
    private val errorMessageMapper: ErrorMessageMapper
) : SimpleMiddleware<Command.LoadInitialData, Event.Internal> {

    override fun execute(command: Command.LoadInitialData): Observable<Event.Internal> =
        characterDetailsRepository.getCharacterById(command.characterId)
            .map<Event.Internal> { characterDetails ->
                Event.Internal.InitialDataLoaded(
                    characterDetailsInitialDataMapper.apply(characterDetails = characterDetails)
                )
            }.onErrorResumeNext {
                Timber.e(it)
                Observable.just(Event.Internal.InitialDataError(errorMessageMapper.apply(it)))
            }
}
