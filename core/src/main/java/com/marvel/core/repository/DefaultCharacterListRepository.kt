package com.marvel.core.repository

import com.marvel.common.error.ErrorMessageMapper
import com.marvel.core.database.Outcome
import com.marvel.core.database.characters.source.LocalCharacterDataSource
import com.marvel.core.remote.characters.mapper.CharacterListDomainMapper
import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.core.remote.characters.source.RemoteCharacterDataSource
import com.marvel.core.usecase.GetHashForRemoteServiceUseCase
import com.marvel.home.domain.model.HomeErrorData
import com.marvel.home.domain.model.MarvelCharacter
import com.marvel.home.repository.CharacterListRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

/**
 * Main repository for handling characters data. Checks if the data is available offline in cache then
 * first provides it from there later checks the API too and then notifies the observer.
 *
 * Also provides the errors info in-case of any failure. If its a main error then there is no data
 * available and screen only shows error else we show toast/snackBar message for error.
 */
class DefaultCharacterListRepository @Inject constructor(
    private val remoteCharacterDataSource: RemoteCharacterDataSource,
    private val localCharacterDataSource: LocalCharacterDataSource,
    private val getHashForRemoteServiceUseCase: GetHashForRemoteServiceUseCase,
    private val errorMessageMapper: ErrorMessageMapper,
    private val characterListDomainMapper: CharacterListDomainMapper
) : CharacterListRepository {

    private val dataSubject: BehaviorSubject<List<MarvelCharacter>> = BehaviorSubject.create()
    override val data: Observable<List<MarvelCharacter>> = dataSubject

    private val errorSubject: PublishSubject<HomeErrorData> = PublishSubject.create()
    override val error: Observable<HomeErrorData> = errorSubject

    override fun startObserving(params: CharacterListRepository.Params): Completable =
        checkAndGetFromRemoteAndCache(params) {
            fetchFromRemoteAndCache(params)
        }

    private fun checkAndGetFromRemoteAndCache(
        params: CharacterListRepository.Params,
        fetchCall: (CharacterListRepository.Params) -> Observable<List<CharacterRaw>>
    ): Completable {
        val charactersRawObservable = if (params.forceFetch) {
            fetchCall(params)
        } else {
            localCharacterDataSource.readCharacters().flatMap { outcome ->
                if (outcome is Outcome.Success) {
                    dataSubject.onNext(outcome.value.convert())
                }
                fetchCall(params)
            }
        }
        return charactersRawObservable.flatMapCompletable {
            dataSubject.onNext(it.convert())
            Completable.complete()
        }.doOnError {
            errorSubject.onNext(
                HomeErrorData(
                    message = errorMessageMapper.apply(it),
                    isMainError = dataSubject.value.isNullOrEmpty()
                )
            )
        }
    }

    private fun List<CharacterRaw>.convert() = characterListDomainMapper
        .toDomain(this)

    private fun fetchFromRemoteAndCache(params: CharacterListRepository.Params)
            : Observable<List<CharacterRaw>> {
        return getHashForRemoteServiceUseCase.get(
            GetHashForRemoteServiceUseCase.Params(
                timestamp = params.timestamp,
                publicApiKey = params.apiPublicKey,
                privateApiKey = params.apiPrivateKey
            )
        ).flatMapObservable { hash ->
            remoteCharacterDataSource.fetchCharacters(
                apiKey = params.apiPublicKey,
                timestamp = params.timestamp,
                hash = hash
            ).toObservable().flatMap {
                localCharacterDataSource
                    .saveCharacters(it)
                    .andThen(Observable.just(it))
            }
        }
    }
}