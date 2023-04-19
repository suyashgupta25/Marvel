package com.marvel.home.reducer

import com.marvel.common.tea.dslreducer.ScreenDslReducer
import com.marvel.home.ui.HomeEvent
import com.marvel.home.ui.model.CharacterDetails
import com.marvel.home.ui.model.HomeUiModel
import com.marvel.home.ui.model.SnackBarStatus
import timber.log.Timber
import javax.inject.Inject
import com.marvel.home.ui.HomeCommand as Command
import com.marvel.home.ui.HomeEvent as Event
import com.marvel.home.ui.HomeState as State

internal class HomeReducer @Inject constructor() :
    ScreenDslReducer<Event, Event.Ui, Event.Internal, State, Command>(
        Event.Ui::class,
        Event.Internal::class
    ) {

    override fun Result.ui(event: Event.Ui) = when (event) {
        is Event.Ui.Init -> {
            state { copy(uiModel = HomeUiModel.EMPTY) }
            commands {
                +Command.LoadInitialData
                +Command.ObserveCharactersData(isRefresh = false)
                +Command.ObserveBookmarksData
                +Command.ObserveCharacterErrors
            }
        }

        is Event.Ui.OnCharacterClick -> state {
            copy(
                characterDetails = CharacterDetails(
                    detailsScreenCharacterId = event.id,
                    shouldShow = true
                )
            )
        }

        HomeEvent.Ui.OnRefreshTriggered -> commands {
            +Command.ObserveCharactersData(isRefresh = true)
        }

        HomeEvent.Ui.AfterCharacterClicked -> state {
            copy(
                characterDetails = CharacterDetails.EMPTY
            )
        }

        is HomeEvent.Ui.OnBookmarkClick -> commands {
            +Command.BookmarkAction(id = event.id, isBookmarked = event.isBookmarked)
        }

        HomeEvent.Ui.ShownConsecutiveDataError -> state {
            copy(snackBarStatus = SnackBarStatus.EMPTY)
        }
    }

    override fun Result.internal(event: Event.Internal) = when (event) {
        is Event.Internal.InitialDataLoaded -> state {
            copy(
                uiModel = event.model,
                isRefreshing = false
            )
        }

        is Event.Internal.InitialDataError -> state {
            copy(
                uiModel = HomeUiModel.FullScreenError(
                    toolbarTitle = event.toolbarTitle,
                    message = event.message
                )
            )
        }

        is HomeEvent.Internal.ShowConsecutiveDataError -> state {
            copy(
                snackBarStatus = SnackBarStatus(
                    message = event.message,
                    shouldShow = event.shouldShow
                )
            )
        }

        is HomeEvent.Internal.ObservingCharactersData -> state {
            copy(isRefreshing = event.shouldRefresh)
        }

        is HomeEvent.Internal.UpdatedBookmark -> Timber.d("Bookmark updated ${event.id}")

        HomeEvent.Internal.ObservingBookmarksData -> Timber.d("Started observing bookmarks data")
        HomeEvent.Internal.DoNothing -> Timber.d("Doing nothing")
    }
}
