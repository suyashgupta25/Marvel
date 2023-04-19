package com.marvel.home.ui

import com.marvel.home.ui.model.CharacterDetails
import com.marvel.home.ui.model.HomeUiModel
import com.marvel.home.ui.model.SnackBarStatus

internal data class HomeState(
    val uiModel: HomeUiModel,
    val snackBarStatus: SnackBarStatus,
    val isRefreshing: Boolean,
    val characterDetails: CharacterDetails
) {

    companion object {

        val EMPTY = HomeState(
            uiModel = HomeUiModel.EMPTY,
            snackBarStatus = SnackBarStatus.EMPTY,
            isRefreshing = false,
            characterDetails = CharacterDetails.EMPTY
        )
    }
}
