package com.marvel.characterdetails.ui

import com.marvel.characterdetails.ui.model.CharacterDetailsSuccessUiModel

internal sealed class CharacterDetailsEvent {

    sealed class Ui : CharacterDetailsEvent() {

        data class Init(val characterId: Int) : Ui()
    }

    sealed class Internal : CharacterDetailsEvent() {

        data class InitialDataLoaded(val uiModel: CharacterDetailsSuccessUiModel) : Internal()

        data class InitialDataError(val errorMessage: String) : Internal()
    }
}
