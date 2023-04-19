package com.marvel.core.database.characters.mapper

import com.marvel.core.database.characters.CharacterEntity
import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.core.remote.characters.model.ThumbnailRaw
import javax.inject.Inject

/**
 * Converts the character domain object to database entity and vice-versa
 */
class CharacterDbDomainMapper @Inject constructor() {

    fun toDomain(characterEntity: CharacterEntity): CharacterRaw = CharacterRaw(
        id = characterEntity.id,
        name = characterEntity.name,
        description = characterEntity.description ?: "",
        thumbnailRaw = ThumbnailRaw(
            characterEntity.thumbnailPath,
            characterEntity.thumbnailExtension
        ),
        modified = characterEntity.modified ?: "",
        resourceUri = characterEntity.resourceUri ?: ""
    )

    fun toEntity(characterRaw: CharacterRaw): CharacterEntity = CharacterEntity(
        id = characterRaw.id ?: 0,
        name = characterRaw.name ?: "",
        description = characterRaw.description ?: "",
        thumbnailPath = characterRaw.thumbnailRaw?.path ?: "",
        thumbnailExtension = characterRaw.thumbnailRaw?.extension ?: "",
        modified = characterRaw.modified ?: "",
        resourceUri = characterRaw.resourceUri ?: ""
    )
}