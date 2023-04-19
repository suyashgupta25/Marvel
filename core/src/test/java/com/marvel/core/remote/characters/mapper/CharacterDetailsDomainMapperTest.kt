package com.marvel.core.remote.characters.mapper

import com.marvel.characterdetails.domain.model.MarvelCharacterDetails
import com.marvel.core.remote.characters.model.CharacterRaw
import org.junit.Test
import kotlin.test.assertEquals

class CharacterDetailsDomainMapperTest {

    private val mapper = CharacterDetailsDomainMapper()

    @Test
    fun `returns character details`() {
        val id = 0
        val description = "description"
        val modified = "modified"
        val characterRaw = CharacterRaw(
            id = id,
            name = null,
            description = description,
            modified = modified,
            thumbnailRaw = null
        )
        val expectedValue = MarvelCharacterDetails(
            id = id,
            name = "",
            description = description,
            imageUrl = "",
            lastUpdated = modified
        )

        val domain = mapper.toDomain(characterRaw)

        assertEquals(expected = expectedValue, actual = domain)
    }
}