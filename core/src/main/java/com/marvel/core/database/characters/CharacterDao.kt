package com.marvel.core.database.characters

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character LIMIT 20")
    fun getCharacters(): Single<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE id=:id ")
    fun getCharacterById(id: Int): Single<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characters: List<CharacterEntity>): Completable

    @Update
    fun update(character: CharacterEntity): Completable
}