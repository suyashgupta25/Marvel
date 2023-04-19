package com.marvel.home.ui.mapper

import com.marvel.common.util.StringProvider
import com.marvel.home.domain.model.MarvelCharacter
import com.marvel.home.ui.model.CharacterUiModel
import com.marvel.home.ui.model.HomeUiModel
import javax.inject.Inject

internal class HomeUiModelMapper @Inject constructor(private val stringProvider: StringProvider) {

    fun toUiModel(characters: List<MarvelCharacter>, bookmarks: List<Int>) = HomeUiModel.Success(
        toolbarTitle = stringProvider.getHomeScreenTitle(),
        characters = characters.map {
            CharacterUiModel(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                isBookmarked = bookmarks.contains(it.id)
            )
        }
    )
}