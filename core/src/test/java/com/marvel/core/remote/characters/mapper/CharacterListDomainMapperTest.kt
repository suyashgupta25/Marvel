package com.marvel.core.remote.characters.mapper

import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.home.domain.model.MarvelCharacter
import org.junit.Test
import kotlin.test.assertEquals

class CharacterListDomainMapperTest {

    private val mapper = CharacterListDomainMapper()

    @Test
    fun `returns characters list`() {
        val id = 0
        val name = "name"
        val description = "description"
        val modified = "modified"
        val characterRaw = CharacterRaw(
            id = id,
            name = name,
            description = description,
            modified = modified,
            thumbnailRaw = null
        )
        val expectedValue = MarvelCharacter(
            id = id,
            name = name,
            description = description,
            imageUrl = "",
            lastUpdated = modified,
            webProfileLink = ""
        )

        val domain = mapper.toDomain(listOf(characterRaw))

        assertEquals(expected = listOf(expectedValue), actual = domain)
    }

    @Test
    fun `returns empty characters list`() {
        val description = "description"
        val modified = "modified"
        val characterRaw = CharacterRaw(
            id = null,
            name = null,
            description = description,
            modified = modified,
            thumbnailRaw = null
        )

        val domain = mapper.toDomain(listOf(characterRaw))

        assertEquals(expected = emptyList(), actual = domain)
    }
}