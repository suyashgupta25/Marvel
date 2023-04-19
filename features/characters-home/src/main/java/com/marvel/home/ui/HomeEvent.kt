package com.marvel.home.ui

import com.marvel.home.ui.model.HomeUiModel

internal sealed class HomeEvent {

    sealed class Ui : HomeEvent() {

        object Init : Ui()

        data class OnCharacterClick(val id: Int) : Ui()

        data class OnBookmarkClick(val id: Int, val isBookmarked: Boolean) : Ui()

        object AfterCharacterClicked : Ui()

        object OnRefreshTriggered : Ui()

        object ShownConsecutiveDataError : Ui()
    }

    sealed class Internal : HomeEvent() {

        data class ObservingCharactersData(val shouldRefresh: Boolean) : Internal()

        object ObservingBookmarksData : Internal()

        data class InitialDataLoaded(val model: HomeUiModel) : Internal()

        data class InitialDataError(val toolbarTitle: String, val message: String) : Internal()

        data class ShowConsecutiveDataError(val message: String, val shouldShow: Boolean) :
            Internal()

        data class UpdatedBookmark(val id: Int) : Internal()

        object DoNothing : Internal()
    }
}
