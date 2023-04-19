package com.marvel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marvel.core.BuildConfig
import com.marvel.core.database.bookmark.BookmarkDao
import com.marvel.core.database.bookmark.BookmarkEntity
import com.marvel.core.database.characters.CharacterDao
import com.marvel.core.database.characters.CharacterEntity

@Database(
    entities = [CharacterEntity::class, BookmarkEntity::class],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = BuildConfig.DATABASE_EXPORT_SCHEMA
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun bookmarkDao(): BookmarkDao
}