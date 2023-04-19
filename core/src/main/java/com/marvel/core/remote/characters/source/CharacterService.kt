package com.marvel.core.remote.characters.source

import com.marvel.core.remote.characters.model.CharactersRaw
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface contains all api related to characters
 */
interface CharacterService {

    /**
     * Fetches lists of comic characters with optional filters.
     *
     * @param apikey key required to authenticate the request
     * @param timestamp current timestamp
     * @param hash a md5 digest of the parameters
     * @param name Return only characters matching the specified full character name(e.g. Spider-Man).
     * @param nameStartsWith Return characters with names that begin with the specified string (e.g. Sp).
     * @param modifiedSince Return only characters which have been modified since the specified date.
     * @param comics Return only characters which appear in the specified comics
     * (accepts a comma-separated list of ids).
     * @param series Return only characters which appear the specified series
     * (accepts a comma-separated list of ids).
     * @param events Return only characters which appear in the specified events
     * (accepts a comma-separated list of ids).
     * @param stories Return only characters which appear the specified stories
     * (accepts a comma-separated list of ids).
     * @param orderBy Order the result set by a field or fields. Add a "-" to the value sort in
     * descending order. Multiple values are given priority in the order in which they are passed.
     * @param limit Limit the result set to the specified number of resources.
     * @param offset Skip the specified number of resources in the result set.
     *
     * @return HTTP 200 - response will be returned with json data
     *
     * Error details:
     * 409	Limit greater than 100.
     * 409	Limit invalid or below 1.
     * 409	Invalid or unrecognized parameter.
     * 409	Empty parameter.
     * 409	Invalid or unrecognized ordering parameter.
     * 409	Too many values sent to a multi-value list filter.
     * 409	Invalid value passed to filter.
     */
    @GET("v1/public/characters")
    fun fetchCharacters(
        @Query("apikey") apikey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("name") name: String? = null,
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("modifiedSince") modifiedSince: String? = null,
        @Query("comics") comics: Int? = null,
        @Query("series") series: Int? = null,
        @Query("events") events: Int? = null,
        @Query("stories") stories: Int? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Single<CharactersRaw>
}