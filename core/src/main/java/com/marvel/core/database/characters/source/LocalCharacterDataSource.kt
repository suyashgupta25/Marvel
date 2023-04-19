package com.marvel.core.database.characters.source

import com.marvel.core.database.DbError
import com.marvel.core.database.Outcome
import com.marvel.core.database.characters.CharacterDao
import com.marvel.core.database.characters.mapper.CharacterDbDomainMapper
import com.marvel.core.remote.characters.model.CharacterRaw
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Handles all database operations for the characters
 */
@Singleton
class LocalCharacterDataSource @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterDbDomainMapper: CharacterDbDomainMapper
) {

    fun saveCharacters(charactersRaw: List<CharacterRaw>): Completable =
        characterDao.insert(charactersRaw
            .filter { !it.name.isNullOrEmpty() && it.id != null }
            .distinctBy { it.id }.map { domainObj ->
                characterDbDomainMapper.toEntity(
                    domainObj
                )
            })

    fun readCharacters(): Observable<Outcome<List<CharacterRaw>, DbError>> =
        characterDao.getCharacters().flatMap {
            val result = if (it.isEmpty())
                Outcome.Error(DbError)
            else
                Outcome.Success(it.map { entity -> characterDbDomainMapper.toDomain(entity) })
            return@flatMap Single.just(result)
        }.onErrorReturn {
            Timber.e(it)
            Outcome.Error(DbError)
        }.toObservable()

    fun getCharacterById(id: Int): Observable<Outcome<CharacterRaw, DbError>> =
        characterDao.getCharacterById(id).flatMap {
            return@flatMap Single.just<Outcome<CharacterRaw, DbError>>(
                Outcome.Success(characterDbDomainMapper.toDomain(it))
            )
        }.onErrorReturn {
            Timber.e(it)
            Outcome.Error(DbError)
        }.toObservable()

}