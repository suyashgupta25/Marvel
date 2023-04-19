package com.marvel.characterdetails.ui.model

internal data class CharacterDetailsState(
    val uiModel: CharacterDetailsUiModel
) {

    companion object {

        val EMPTY = CharacterDetailsState(
            uiModel = CharacterDetailsUiModel.EMPTY
        )
    }
}
