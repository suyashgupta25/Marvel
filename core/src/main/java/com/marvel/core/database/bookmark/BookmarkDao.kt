package com.marvel.core.database.bookmark

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks(): Single<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmarkEntity: BookmarkEntity): Completable

    @Delete
    fun delete(bookmarkEntity: BookmarkEntity): Completable
}