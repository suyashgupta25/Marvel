package com.marvel.core.remote.characters.mapper

import com.marvel.characterdetails.domain.model.MarvelCharacterDetails
import com.marvel.core.remote.characters.model.CharacterRaw
import javax.inject.Inject

/**
 * Converts character data object to domain object of character details
 */
class CharacterDetailsDomainMapper @Inject constructor() {

    fun toDomain(characterRaw: CharacterRaw) = MarvelCharacterDetails(
        id = characterRaw.id ?: 0,
        name = characterRaw.name ?: "",
        description = characterRaw.description ?: "",
        imageUrl = characterRaw.thumbnailRaw?.let { thumb -> "${thumb.path}.${thumb.extension}" }
            ?: "",
        lastUpdated = characterRaw.modified ?: ""
    )
}