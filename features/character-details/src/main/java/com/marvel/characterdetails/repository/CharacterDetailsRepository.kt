package com.marvel.characterdetails.repository

import com.marvel.characterdetails.domain.model.MarvelCharacterDetails
import io.reactivex.rxjava3.core.Observable

interface CharacterDetailsRepository {

    fun getCharacterById(id: Int): Observable<MarvelCharacterDetails>
}