package com.marvel.home.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface BookmarksRepository {

    val data: Observable<List<Int>>

    fun startObservingBookmarks(): Completable

    fun addBookmark(id: Int): Completable

    fun removeBookmark(id: Int): Completable
}