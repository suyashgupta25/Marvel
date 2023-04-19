package com.marvel.home.ui.middleware

import com.marvel.common.tea.MiddlewareDelegate
import com.marvel.home.ui.HomeCommand
import javax.inject.Inject
import com.marvel.home.ui.HomeCommand as Command
import com.marvel.home.ui.HomeEvent as Event

internal class HomeMiddlewareDelegate @Inject constructor(
    private val loadInitialDataMiddleware: HomeLoadInitialDataMiddleware,
    private val updateDataMiddleware: HomeObserveCharactersDataMiddleware,
    private val bookmarkActionMiddleware: HomeBookmarkActionMiddleware,
    private val observeBookmarksDataMiddleware: HomeObserveBookmarksDataMiddleware,
    private val homeCheckErrorsMiddleware: HomeCheckErrorsMiddleware
) : MiddlewareDelegate<Command, Event.Internal> {

    override fun processCommands(command: Command) = when (command) {
        is Command.LoadInitialData -> loadInitialDataMiddleware.execute(command)
        is Command.ObserveCharactersData -> updateDataMiddleware.execute(command)
        is HomeCommand.BookmarkAction -> bookmarkActionMiddleware.execute(command)
        is HomeCommand.ObserveBookmarksData -> observeBookmarksDataMiddleware.execute(command)
        is HomeCommand.ObserveCharacterErrors -> homeCheckErrorsMiddleware.execute(command)
    }
}
