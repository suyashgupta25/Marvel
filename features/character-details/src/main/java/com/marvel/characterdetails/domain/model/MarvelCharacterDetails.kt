package com.marvel.characterdetails.domain.model

data class MarvelCharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val lastUpdated: String
)