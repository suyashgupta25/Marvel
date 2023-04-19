package com.marvel.characterdetails.ui

internal sealed class CharacterDetailsCommand {

    data class LoadInitialData(val characterId: Int) : CharacterDetailsCommand()
}
