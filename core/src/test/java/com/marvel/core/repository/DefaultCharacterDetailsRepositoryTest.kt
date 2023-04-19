package com.marvel.core.repository

import com.marvel.characterdetails.domain.model.MarvelCharacterDetails
import com.marvel.common.error.NoDataException
import com.marvel.core.database.DbError
import com.marvel.core.database.Outcome
import com.marvel.core.database.characters.source.LocalCharacterDataSource
import com.marvel.core.remote.characters.mapper.CharacterDetailsDomainMapper
import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.testing.mockRelaxed
import io.reactivex.rxjava3.core.Observable
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DefaultCharacterDetailsRepositoryTest {

    private val localCharacterDataSource: LocalCharacterDataSource = mock()
    private val characterDetailsDomainMapper: CharacterDetailsDomainMapper = mock()
    private val repository = DefaultCharacterDetailsRepository(
        localCharacterDataSource,
        characterDetailsDomainMapper
    )

    @Test
    fun `Given id of a character Then returns successfully with its details`() {
        val characterId = 1001
        val characterRaw = mockRelaxed<CharacterRaw>()
        val expectedValue = mockRelaxed<MarvelCharacterDetails>()
        ArrangeBuilder()
            .withLocalCharacterDataSourceSuccess(characterId, characterRaw)
            .withCharacterDetailsDomainMapper(characterRaw, expectedValue)

        val testObserver = repository.getCharacterById(characterId).test()

        testObserver.assertResult(expectedValue)
    }

    @Test
    fun `Given id of a character Then returns error`() {
        val characterId = 1001
        ArrangeBuilder()
            .withLocalCharacterDataSourceError(characterId)

        val testObserver = repository.getCharacterById(characterId).test()

        testObserver.assertError { it is NoDataException }
    }

    private inner class ArrangeBuilder {

        fun withLocalCharacterDataSourceSuccess(characterId: Int, characterRaw: CharacterRaw) =
            apply {
                whenever(localCharacterDataSource.getCharacterById(characterId)).thenReturn(
                    Observable.just(Outcome.Success(characterRaw))
                )
            }

        fun withLocalCharacterDataSourceError(characterId: Int) =
            apply {
                whenever(localCharacterDataSource.getCharacterById(characterId)).thenReturn(
                    Observable.just(Outcome.Error(DbError))
                )
            }

        fun withCharacterDetailsDomainMapper(
            characterRaw: CharacterRaw,
            marvelCharacterDetails: MarvelCharacterDetails
        ) =
            apply {
                whenever(characterDetailsDomainMapper.toDomain(characterRaw)).thenReturn(
                    marvelCharacterDetails
                )
            }
    }
}