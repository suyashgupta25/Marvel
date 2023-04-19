package com.marvel.core.remote.characters.model

import com.squareup.moshi.Json

data class DataRaw(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    @Json(name = "results")
    val characters: List<CharacterRaw>? = null,
)