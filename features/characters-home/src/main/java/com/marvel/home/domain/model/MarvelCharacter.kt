package com.marvel.home.domain.model

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val lastUpdated: String,
    val webProfileLink: String
)