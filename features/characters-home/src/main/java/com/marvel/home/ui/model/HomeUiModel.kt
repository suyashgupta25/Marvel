package com.marvel.home.ui.model

internal sealed class HomeUiModel(open val toolbarTitle: String) {

    data class Loading(override val toolbarTitle: String) : HomeUiModel(toolbarTitle)

    data class FullScreenError(override val toolbarTitle: String, val message: String) :
        HomeUiModel(toolbarTitle)

    data class Success(override val toolbarTitle: String, val characters: List<CharacterUiModel>) :
        HomeUiModel(toolbarTitle)

    companion object {

        val EMPTY = Loading("")
    }
}
