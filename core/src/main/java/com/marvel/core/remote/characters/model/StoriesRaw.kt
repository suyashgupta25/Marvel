package com.marvel.core.remote.characters.model

import com.squareup.moshi.Json

data class StoriesRaw(
    val available: Int? = null,
    @Json(name = "collectionURI")
    val collectionUri: String? = null,
    val items: List<ItemRaw>? = null,
    val returned: Int? = null,
)