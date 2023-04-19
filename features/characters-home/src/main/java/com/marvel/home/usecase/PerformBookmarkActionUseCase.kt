package com.marvel.home.usecase

import com.marvel.common.domain.CompletableUseCase
import com.marvel.home.repository.BookmarksRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

internal class PerformBookmarkActionUseCase @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) : CompletableUseCase<PerformBookmarkActionUseCase.Params> {

    override fun execute(params: Params): Completable {
        return if (params.isBookmarked) {
            bookmarksRepository.removeBookmark(params.id)
        } else {
            bookmarksRepository.addBookmark(params.id)
        }
    }

    data class Params(val id: Int, val isBookmarked: Boolean)
}
