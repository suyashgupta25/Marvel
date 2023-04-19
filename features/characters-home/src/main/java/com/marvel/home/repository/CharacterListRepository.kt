package com.marvel.home.repository

import com.marvel.home.domain.model.HomeErrorData
import com.marvel.home.domain.model.MarvelCharacter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface CharacterListRepository {

    val data: Observable<List<MarvelCharacter>>

    val error: Observable<HomeErrorData>

    fun startObserving(params: Params): Completable

    data class Params(
        val forceFetch: Boolean,
        val timestamp: String,
        val apiPublicKey: String,
        val apiPrivateKey: String
    )
}
