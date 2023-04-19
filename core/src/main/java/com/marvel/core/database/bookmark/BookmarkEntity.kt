package com.marvel.core.database.bookmark

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
class BookmarkEntity(
    @PrimaryKey
    val id: Int
)