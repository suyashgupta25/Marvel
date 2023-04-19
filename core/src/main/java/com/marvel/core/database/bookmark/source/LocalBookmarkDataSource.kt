package com.marvel.core.database.bookmark.source

import com.marvel.core.database.DbError
import com.marvel.core.database.Outcome
import com.marvel.core.database.bookmark.BookmarkDao
import com.marvel.core.database.bookmark.BookmarkEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import timber.log.Timber
import javax.inject.Inject

class LocalBookmarkDataSource @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkDataSource {

    override fun getAllBookmarkIds(): Observable<Outcome<List<Int>, DbError>> =
        bookmarkDao.getAllBookmarks().flatMap {
            val result = if (it.isEmpty())
                Outcome.Error(DbError)
            else
                Outcome.Success(it.map { entity -> entity.id })
            return@flatMap Single.just(result)
        }.onErrorReturn {
            Timber.e(it)
            Outcome.Error(DbError)
        }.toObservable()

    override fun addBookmark(id: Int): Completable = bookmarkDao.insert(BookmarkEntity(id))

    override fun removeBookmark(id: Int): Completable = bookmarkDao.delete(BookmarkEntity(id))
}