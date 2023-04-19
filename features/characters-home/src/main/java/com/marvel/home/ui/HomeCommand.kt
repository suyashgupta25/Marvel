package com.marvel.home.ui

internal sealed class HomeCommand {

    object LoadInitialData : HomeCommand()

    data class ObserveCharactersData(val isRefresh: Boolean) : HomeCommand()

    object ObserveBookmarksData : HomeCommand()

    object ObserveCharacterErrors : HomeCommand()

    data class BookmarkAction(val id: Int, val isBookmarked: Boolean) : HomeCommand()
}
