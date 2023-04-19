package com.marvel.home.usecase

import com.marvel.common.domain.CompletableUseCase
import com.marvel.home.repository.BookmarksRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class ObserveBookmarksUpdateUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) : CompletableUseCase<Unit> {

    override fun execute(params: Unit): Completable =
        bookmarksRepository.startObservingBookmarks()
}
