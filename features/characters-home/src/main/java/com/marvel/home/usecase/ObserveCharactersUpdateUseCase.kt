package com.marvel.home.usecase

import com.marvel.common.domain.CompletableUseCase
import com.marvel.common.util.StringProvider
import com.marvel.home.repository.CharacterListRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class ObserveCharactersUpdateUseCase @Inject constructor(
    private val characterListRepository: CharacterListRepository,
    private val stringProvider: StringProvider
) : CompletableUseCase<ObserveCharactersUpdateUseCase.Params> {

    override fun execute(params: Params): Completable =
        characterListRepository.startObserving(
            CharacterListRepository.Params(
                forceFetch = params.forceFetch,
                timestamp = stringProvider.getCurrentTimestamp(),
                apiPublicKey = stringProvider.getMarvelPublicApiKey(),
                apiPrivateKey = stringProvider.getMarvelPrivateApiKey()
            )
        )

    data class Params(val forceFetch: Boolean)
}
