package com.marvel.core.database.characters

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val thumbnailPath: String?,
    val thumbnailExtension: String?,
    val resourceUri: String?
)