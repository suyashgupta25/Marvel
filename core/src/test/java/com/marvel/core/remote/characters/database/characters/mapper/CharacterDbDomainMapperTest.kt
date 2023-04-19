package com.marvel.core.remote.characters.database.characters.mapper

import com.marvel.core.database.characters.CharacterEntity
import com.marvel.core.database.characters.mapper.CharacterDbDomainMapper
import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.core.remote.characters.model.ThumbnailRaw
import org.junit.Test
import kotlin.test.assertEquals

class CharacterDbDomainMapperTest {

    private val mapper = CharacterDbDomainMapper()

    @Test
    fun `toDomain returns characters domain`() {
        val id = 0
        val description = "description"
        val modified = "modified"
        val thumbnailPath = "www.marvel.com/thor"
        val thumbnailExtension = ".jpg"
        val entity = CharacterEntity(
            id = id,
            name = "",
            description = description,
            modified = modified,
            thumbnailPath = thumbnailPath,
            thumbnailExtension = thumbnailExtension,
            resourceUri = ""
        )
        val expectedRaw = CharacterRaw(
            id = id,
            name = "",
            description = description,
            modified = modified,
            thumbnailRaw = ThumbnailRaw(thumbnailPath, thumbnailExtension),
            resourceUri = ""
        )

        val toDomain = mapper.toDomain(entity)

        assertEquals(expectedRaw, toDomain)
    }

    @Test
    fun `toEntity returns characters entity`() {
        val id = 0
        val description = "description"
        val modified = "modified"
        val thumbnailPath = "www.marvel.com/thor"
        val thumbnailExtension = ".jpg"
        val raw = CharacterRaw(
            id = id,
            name = "",
            description = description,
            modified = modified,
            thumbnailRaw = ThumbnailRaw(thumbnailPath, thumbnailExtension),
            resourceUri = ""
        )
        val expectedEntity = CharacterEntity(
            id = id,
            name = "",
            description = description,
            modified = modified,
            thumbnailPath = thumbnailPath,
            thumbnailExtension = thumbnailExtension,
            resourceUri = ""
        )

        val toEntity = mapper.toEntity(raw)

        assertEquals(expectedEntity, toEntity)
    }

}