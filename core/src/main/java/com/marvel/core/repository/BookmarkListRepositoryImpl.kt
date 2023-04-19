package com.marvel.core.repository

import com.marvel.core.database.Outcome
import com.marvel.core.database.bookmark.source.LocalBookmarkDataSource
import com.marvel.home.repository.BookmarksRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Main repository of bookmarks which handles actions(add/remove) and provides bookmarked list
 */
class BookmarkListRepositoryImpl @Inject constructor(
    private val localBookmarkDataSource: LocalBookmarkDataSource
) : BookmarksRepository {

    private val subject: BehaviorSubject<List<Int>> = BehaviorSubject.create()
    override val data: Observable<List<Int>> = subject

    override fun startObservingBookmarks() =
        localBookmarkDataSource.getAllBookmarkIds().map { outcome ->
            if (outcome is Outcome.Success) {
                subject.onNext(outcome.value)
            } else {
                subject.onNext(emptyList())
            }
        }.flatMapCompletable {
            Completable.complete()
        }

    override fun addBookmark(id: Int): Completable =
        localBookmarkDataSource.addBookmark(id).andThen {
            val bookmarks = subject.value?.toMutableList() ?: mutableListOf()
            bookmarks.add(id)
            subject.onNext(bookmarks)
        }

    override fun removeBookmark(id: Int): Completable =
        localBookmarkDataSource.removeBookmark(id).andThen {
            val bookmarks = subject.value?.toMutableList() ?: mutableListOf()
            bookmarks.remove(id)
            subject.onNext(bookmarks)
        }
}