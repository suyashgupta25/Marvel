package com.marvel.characterdetails.ui.mapper

import com.marvel.characterdetails.domain.model.MarvelCharacterDetails
import com.marvel.characterdetails.ui.model.CharacterDetailsSuccessUiModel
import com.marvel.common.util.DateUtils
import com.marvel.common.util.StringProvider
import javax.inject.Inject

internal class CharacterDetailsInitialDataMapper @Inject constructor(
    private val stringProvider: StringProvider,
    private val dateUtils: DateUtils
) {

    fun apply(characterDetails: MarvelCharacterDetails) =
        CharacterDetailsSuccessUiModel(
            id = characterDetails.id,
            name = characterDetails.name,
            description = characterDetails.description,
            imageUrl = characterDetails.imageUrl,
            lastUpdated = "${stringProvider.getLastUpdatedDateSuffix()} ${
                dateUtils.formatServerDate(
                    characterDetails.lastUpdated
                )
            }"
        )
}