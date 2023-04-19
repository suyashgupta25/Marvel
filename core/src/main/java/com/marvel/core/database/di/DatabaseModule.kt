package com.marvel.core.database.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.marvel.core.BuildConfig.DATABASE_NAME
import com.marvel.core.database.AppDatabase
import com.marvel.core.database.bookmark.BookmarkDao
import com.marvel.core.database.bookmark.source.BookmarkDataSource
import com.marvel.core.database.bookmark.source.LocalBookmarkDataSource
import com.marvel.core.database.characters.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCharacterDatabase(application: Application?): AppDatabase {
        return databaseBuilder(application!!, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideBookmarkDao(appDatabase: AppDatabase): BookmarkDao {
        return appDatabase.bookmarkDao()
    }

    @Provides
    @Singleton
    fun provideBookmarkDataSource(bookmarkDao: BookmarkDao): BookmarkDataSource {
        return LocalBookmarkDataSource(bookmarkDao)
    }
}