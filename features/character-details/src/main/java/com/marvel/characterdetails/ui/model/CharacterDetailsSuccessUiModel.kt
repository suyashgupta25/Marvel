package com.marvel.characterdetails.ui.model

data class CharacterDetailsSuccessUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val lastUpdated: String,
    val imageUrl: String
)