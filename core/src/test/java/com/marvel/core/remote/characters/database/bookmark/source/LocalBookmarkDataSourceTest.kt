package com.marvel.core.remote.characters.database.bookmark.source

import com.marvel.core.database.DbError
import com.marvel.core.database.Outcome
import com.marvel.core.database.bookmark.BookmarkDao
import com.marvel.core.database.bookmark.BookmarkEntity
import com.marvel.core.database.bookmark.source.LocalBookmarkDataSource
import com.marvel.testing.mockRelaxed
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LocalBookmarkDataSourceTest {

    private val bookmarkDao: BookmarkDao = mock()
    private val dataSource = LocalBookmarkDataSource(bookmarkDao)

    @Test
    fun `getAllBookmarkIds returns all bookmarks with success`() {
        val id = 2423
        val entity = mockRelaxed<BookmarkEntity>().apply {
            whenever(this.id) doReturn id
        }
        ArrangeBuilder()
            .withAllBookmarksSuccess(listOf(entity))

        val testObserver = dataSource.getAllBookmarkIds().test()

        testObserver.assertResult(Outcome.Success(listOf(id)))
    }

    @Test
    fun `getAllBookmarkIds returns no bookmarks but error`() {
        ArrangeBuilder()
            .withAllBookmarksSuccess(listOf())

        val testObserver = dataSource.getAllBookmarkIds().test()

        testObserver.assertResult(Outcome.Error(DbError))
    }

    @Test
    fun `getAllBookmarkIds returns error from db`() {
        val error = IllegalArgumentException()
        ArrangeBuilder()
            .withAllBookmarksError(error)

        val testObserver = dataSource.getAllBookmarkIds().test()

        testObserver.assertResult(Outcome.Error(DbError))
    }

    @Test
    fun `addBookmark returns success from db`() {
        val id = 2423
        val result = Completable.complete()
        ArrangeBuilder()
            .withInsertBookmarkSuccess(result)

        val testObserver = dataSource.addBookmark(id).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `addBookmark returns error from db`() {
        val id = 2423
        val error = IllegalArgumentException()
        ArrangeBuilder()
            .withInsertBookmarkError(error)

        val testObserver = dataSource.addBookmark(id).test()

        testObserver.assertError(error)
    }

    @Test
    fun `removeBookmark returns success from db`() {
        val id = 2423
        val result = Completable.complete()
        ArrangeBuilder()
            .withDeleteBookmarkSuccess(result)

        val testObserver = dataSource.removeBookmark(id).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `removeBookmark returns error from db`() {
        val id = 2423
        val error = IllegalArgumentException()
        ArrangeBuilder()
            .withDeleteBookmarkError(error)

        val testObserver = dataSource.removeBookmark(id).test()

        testObserver.assertError(error)
    }

    private inner class ArrangeBuilder {

        fun withAllBookmarksSuccess(list: List<BookmarkEntity>) =
            apply {
                whenever(bookmarkDao.getAllBookmarks()).thenReturn(
                    Single.just(list)
                )
            }

        fun withAllBookmarksError(throwable: Throwable) =
            apply {
                whenever(bookmarkDao.getAllBookmarks()).thenReturn(
                    Single.error(throwable)
                )
            }

        fun withInsertBookmarkSuccess(completable: Completable) =
            apply {
                whenever(bookmarkDao.insert(any())).thenReturn(
                    completable
                )
            }

        fun withInsertBookmarkError(throwable: Throwable) =
            apply {
                whenever(bookmarkDao.insert(any())).thenReturn(
                    Completable.error(throwable)
                )
            }

        fun withDeleteBookmarkSuccess(completable: Completable) =
            apply {
                whenever(bookmarkDao.delete(any())).thenReturn(
                    completable
                )
            }

        fun withDeleteBookmarkError(throwable: Throwable) =
            apply {
                whenever(bookmarkDao.delete(any())).thenReturn(
                    Completable.error(throwable)
                )
            }
    }
}