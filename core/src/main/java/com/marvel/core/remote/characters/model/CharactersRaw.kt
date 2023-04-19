package com.marvel.core.remote.characters.model

import com.squareup.moshi.Json

data class CharactersRaw(
    val code: Int? = null,
    val status: String? = null,
    val copyright: String? = null,
    val attributionText: String? = null,
    @Json(name = "attributionHTML")
    val attributionHtml: String? = null,
    val etag: String? = null,
    @Json(name = "data")
    val dataRaw: DataRaw? = null,
)