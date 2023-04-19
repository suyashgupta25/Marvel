package com.marvel.core.remote.characters.model

import com.squareup.moshi.Json

data class CharacterRaw(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    @Json(name = "thumbnail")
    val thumbnailRaw: ThumbnailRaw? = null,
    @Json(name = "resourceURI")
    val resourceUri: String? = null,
    @Json(name = "comics", ignore = true)
    val comicsRaw: ComicsRaw? = null,
    @Json(name = "series", ignore = true)
    val seriesRaw: SeriesRaw? = null,
    @Json(name = "stories", ignore = true)
    val storiesRaw: StoriesRaw? = null,
    @Json(name = "events", ignore = true)
    val eventsRaw: EventsRaw? = null,
    @Json(name = "urls", ignore = true)
    val urlsRaw: List<UrlRaw>? = null,
)