package com.marvel.characterdetails.ui.model

internal sealed class CharacterDetailsUiModel {

    object Loading : CharacterDetailsUiModel()

    data class FullScreenError(val message: String) : CharacterDetailsUiModel()

    data class Success(val successUiModel: CharacterDetailsSuccessUiModel) :
        CharacterDetailsUiModel()

    companion object {

        val EMPTY = Loading
    }
}
