package com.marvel.core.remote.characters.model

import com.squareup.moshi.Json

data class ItemRaw(
    @Json(name = "resourceURI")
    val resourceUri: String? = null,
    val name: String? = null,
    val type: String? = null,
)