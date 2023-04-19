package com.marvel.core.remote.characters.mapper

import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.home.domain.model.MarvelCharacter
import javax.inject.Inject

/**
 * Converts character data list to domain list of character
 */
class CharacterListDomainMapper @Inject constructor() {

    fun toDomain(charactersRaw: List<CharacterRaw>): List<MarvelCharacter> =
        charactersRaw
            .filter { !it.name.isNullOrEmpty() && it.id != null }
            .distinctBy { it.id }
            .sortedBy { it.name }
            .map {
                val imageUrl = it.thumbnailRaw?.let { thumb -> "${thumb.path}.${thumb.extension}" }
                MarvelCharacter(
                    id = it.id ?: 0,
                    name = it.name ?: "",
                    description = it.description ?: "",
                    imageUrl = imageUrl ?: "",
                    lastUpdated = it.modified ?: "",
                    webProfileLink = it.resourceUri ?: ""
                )
            }
}