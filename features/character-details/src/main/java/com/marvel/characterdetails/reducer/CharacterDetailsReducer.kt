package com.marvel.characterdetails.reducer

import com.marvel.characterdetails.ui.CharacterDetailsEvent
import com.marvel.characterdetails.ui.model.CharacterDetailsUiModel
import com.marvel.common.tea.dslreducer.ScreenDslReducer
import javax.inject.Inject
import com.marvel.characterdetails.ui.CharacterDetailsCommand as Command
import com.marvel.characterdetails.ui.CharacterDetailsEvent as Event
import com.marvel.characterdetails.ui.model.CharacterDetailsState as State

internal class CharacterDetailsReducer @Inject constructor() :
    ScreenDslReducer<Event, Event.Ui, Event.Internal, State, Command>(
        Event.Ui::class,
        Event.Internal::class
    ) {

    override fun Result.ui(event: Event.Ui) = when (event) {
        is Event.Ui.Init -> {
            commands {
                +Command.LoadInitialData(event.characterId)
            }
        }
    }

    override fun Result.internal(event: Event.Internal) = when (event) {
        is Event.Internal.InitialDataLoaded -> state {
            copy(uiModel = CharacterDetailsUiModel.Success(event.uiModel))
        }

        is CharacterDetailsEvent.Internal.InitialDataError -> state {
            copy(uiModel = CharacterDetailsUiModel.FullScreenError(message = event.errorMessage))
        }
    }
}
