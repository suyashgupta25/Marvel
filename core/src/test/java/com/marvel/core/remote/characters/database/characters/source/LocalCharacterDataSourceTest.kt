package com.marvel.core.remote.characters.database.characters.source

import com.marvel.core.database.DbError
import com.marvel.core.database.Outcome
import com.marvel.core.database.characters.CharacterDao
import com.marvel.core.database.characters.CharacterEntity
import com.marvel.core.database.characters.mapper.CharacterDbDomainMapper
import com.marvel.core.database.characters.source.LocalCharacterDataSource
import com.marvel.core.remote.characters.model.CharacterRaw
import com.marvel.testing.mockRelaxed
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LocalCharacterDataSourceTest {

    private val characterDao: CharacterDao = mock()
    private val characterDbDomainMapper: CharacterDbDomainMapper = mock()
    private val dataSource = LocalCharacterDataSource(characterDao, characterDbDomainMapper)

    @Test
    fun `readCharacters returns all characters with success`() {
        val entity = mockRelaxed<CharacterEntity>()
        val raw = mockRelaxed<CharacterRaw>()
        ArrangeBuilder()
            .withAllCharactersAsSuccess(listOf(entity))
            .withCharacterDbDomainMapper(entity, raw)

        val testObserver = dataSource.readCharacters().test()

        testObserver.assertResult(Outcome.Success(listOf(raw)))
    }

    @Test
    fun `readCharacters returns no characters but error`() {
        ArrangeBuilder()
            .withAllCharactersAsSuccess(listOf())

        val testObserver = dataSource.readCharacters().test()

        testObserver.assertResult(Outcome.Error(DbError))
    }

    @Test
    fun `readCharacters returns error from db`() {
        val error = IllegalArgumentException()
        ArrangeBuilder()
            .withAllCharactersError(error)

        val testObserver = dataSource.readCharacters().test()

        testObserver.assertResult(Outcome.Error(DbError))
    }

    @Test
    fun `saveCharacters returns with success`() {
        val entity = mockRelaxed<CharacterEntity>()
        val raw = mockRelaxed<CharacterRaw>().apply {
            whenever(this.id) doReturn 1
            whenever(this.name) doReturn "name"
        }
        val result = Completable.complete()
        ArrangeBuilder()
            .withInsertCharacterAsSuccess(listOf(entity), result)
            .withCharacterDbDomainMapper(raw, entity)

        val testObserver = dataSource.saveCharacters(listOf(raw)).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `saveCharacters returns with error`() {
        val error = IllegalArgumentException()
        val raw = mockRelaxed<CharacterRaw>().apply {
            whenever(this.id) doReturn 1
            whenever(this.name) doReturn "name"
        }
        val entity = mockRelaxed<CharacterEntity>()
        ArrangeBuilder()
            .withInsertCharacterAsError(listOf(entity), error)
            .withCharacterDbDomainMapper(raw, entity)

        val testObserver = dataSource.saveCharacters(listOf(raw)).test()

        testObserver.assertError { it is IllegalArgumentException }
    }

    @Test
    fun `getCharacterById returns error`() {
        val error = IllegalArgumentException()
        val id = 3434
        ArrangeBuilder()
            .withCharacterByIdAsError(id, error)

        val testObserver = dataSource.getCharacterById(id).test()

        testObserver.assertResult(Outcome.Error(DbError))
    }

    @Test
    fun `getCharacterById returns all characters with success`() {
        val id = 3434
        val entity = mockRelaxed<CharacterEntity>()
        val raw = mockRelaxed<CharacterRaw>()
        ArrangeBuilder()
            .withCharacterByIdAsSuccess(id, entity)
            .withCharacterDbDomainMapper(entity, raw)

        val testObserver = dataSource.getCharacterById(id).test()

        testObserver.assertResult(Outcome.Success(raw))
    }

    private inner class ArrangeBuilder {

        fun withAllCharactersAsSuccess(list: List<CharacterEntity>) =
            apply {
                whenever(characterDao.getCharacters()).thenReturn(
                    Single.just(list)
                )
            }

        fun withCharacterDbDomainMapper(fromEntity: CharacterEntity, toCharacterRaw: CharacterRaw) =
            apply {
                whenever(characterDbDomainMapper.toDomain(fromEntity)).thenReturn(
                    toCharacterRaw
                )
            }

        fun withAllCharactersError(throwable: Throwable) =
            apply {
                whenever(characterDao.getCharacters()).thenReturn(
                    Single.error(throwable)
                )
            }

        fun withInsertCharacterAsSuccess(list: List<CharacterEntity>, completable: Completable) =
            apply {
                whenever(characterDao.insert(list)).thenReturn(
                    completable
                )
            }

        fun withCharacterDbDomainMapper(fromCharacterRaw: CharacterRaw, toEntity: CharacterEntity) =
            apply {
                whenever(characterDbDomainMapper.toEntity(fromCharacterRaw)).thenReturn(
                    toEntity
                )
            }

        fun withInsertCharacterAsError(list: List<CharacterEntity>, throwable: Throwable) =
            apply {
                whenever(characterDao.insert(list)).thenReturn(
                    Completable.error(throwable)
                )
            }

        fun withCharacterByIdAsSuccess(id: Int, characterEntity: CharacterEntity) =
            apply {
                whenever(characterDao.getCharacterById(id)).thenReturn(
                    Single.just(characterEntity)
                )
            }

        fun withCharacterByIdAsError(id: Int, throwable: Throwable) =
            apply {
                whenever(characterDao.getCharacterById(id)).thenReturn(
                    Single.error(throwable)
                )
            }
    }
}