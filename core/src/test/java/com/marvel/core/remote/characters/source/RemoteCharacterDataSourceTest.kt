package com.marvel.core.remote.characters.source

import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.core.remote.characters.model.CharactersRaw
import com.marvel.core.remote.characters.model.DataRaw
import com.marvel.testing.mockRelaxed
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteCharacterDataSourceTest {

    private val api: CharacterService = mock()
    private val dataSource = RemoteCharacterDataSource(api)

    @Test
    fun `Given api details Then returns list of raw characters`() {
        val apiKey = "1001"
        val timestamp = "3454353"
        val hash = "3454353asq23ea32"
        val list = mockRelaxed<List<CharacterRaw>>()
        val dataRaw = mockRelaxed<DataRaw>().apply {
            whenever(this.characters) doReturn list
        }
        val charactersRaw = mockRelaxed<CharactersRaw>().apply {
            whenever(this.dataRaw) doReturn dataRaw
        }
        ArrangeBuilder()
            .withRemoteCharacterDataSource(apiKey, timestamp, hash, charactersRaw)

        val testObserver = dataSource.fetchCharacters(apiKey, timestamp, hash).test()

        testObserver.assertResult(list)
    }

    private inner class ArrangeBuilder {

        fun withRemoteCharacterDataSource(
            apiKey: String,
            timestamp: String,
            hash: String,
            charactersRaw: CharactersRaw
        ) =
            apply {
                whenever(api.fetchCharacters(apiKey, timestamp, hash)).thenReturn(
                    Single.just(charactersRaw)
                )
            }

    }
}