package com.marvel.core.database.bookmark.source

import com.marvel.core.database.DbError
import com.marvel.core.database.Outcome
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * The interface provides all the actions related to bookmark functionality
 */
interface BookmarkDataSource {

    fun getAllBookmarkIds(): Observable<Outcome<List<Int>, DbError>>

    fun addBookmark(id: Int): Completable

    fun removeBookmark(id: Int): Completable
}