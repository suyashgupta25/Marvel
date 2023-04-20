package com.marvel.core.repository

import com.marvel.characterdetails.domain.model.MarvelCharacterDetails
import com.marvel.characterdetails.repository.CharacterDetailsRepository
import com.marvel.common.error.NoDataException
import com.marvel.core.database.Outcome
import com.marvel.core.database.characters.source.LocalCharacterDataSource
import com.marvel.core.remote.characters.mapper.CharacterDetailsDomainMapper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * Repository fpr providing character details from local cache based on id
 */
class DefaultCharacterDetailsRepository @Inject constructor(
    private val localCharacterDataSource: LocalCharacterDataSource,
    private val characterDetailsDomainMapper: CharacterDetailsDomainMapper
) : CharacterDetailsRepository {

    override fun getCharacterById(id: Int): Observable<MarvelCharacterDetails> =
        localCharacterDataSource.getCharacterById(id).flatMap { outcome ->
            if (outcome is Outcome.Success) {
                Observable.just(characterDetailsDomainMapper.toDomain(outcome.value))
            } else {
                throw NoDataException()
            }
        }
}