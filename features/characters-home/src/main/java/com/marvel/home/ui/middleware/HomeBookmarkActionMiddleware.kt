package com.marvel.home.ui.middleware

import com.marvel.common.tea.SimpleMiddleware
import com.marvel.home.usecase.PerformBookmarkActionUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import com.marvel.home.ui.HomeCommand as Command
import com.marvel.home.ui.HomeEvent as Event

internal class HomeBookmarkActionMiddleware @Inject constructor(
    private val performBookmarkActionUseCase: PerformBookmarkActionUseCase
) : SimpleMiddleware<Command.BookmarkAction, Event.Internal> {

    override fun execute(command: Command.BookmarkAction): Observable<Event.Internal> =
        performBookmarkActionUseCase.execute(
            PerformBookmarkActionUseCase.Params(
                id = command.id, isBookmarked = command.isBookmarked
            )
        ).andThen(Observable.just(Unit)).map {
            Event.Internal.UpdatedBookmark(command.id)
        }
}
