package com.marvel.core.remote.characters.source

import com.marvel.core.remote.characters.model.CharacterRaw
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Remote data source of character which fetches data from marvel
 */
@Singleton
class RemoteCharacterDataSource @Inject constructor(private val api: CharacterService) {

    fun fetchCharacters(
        apiKey: String,
        timestamp: String,
        hash: String
    ): Single<List<CharacterRaw>> =
        api.fetchCharacters(apikey = apiKey, timestamp = timestamp, hash = hash)
            .map {
                it.dataRaw?.characters ?: emptyList()
            }
}