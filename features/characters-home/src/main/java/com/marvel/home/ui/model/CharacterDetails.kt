package com.marvel.home.ui.model

internal data class CharacterDetails(
    val detailsScreenCharacterId: Int,
    val shouldShow: Boolean
) {

    companion object {

        val EMPTY = CharacterDetails(
            detailsScreenCharacterId = 0,
            shouldShow = false
        )
    }
}