package com.marvel.core.di

import com.marvel.characterdetails.repository.CharacterDetailsRepository
import com.marvel.common.error.ErrorMessageMapper
import com.marvel.core.database.bookmark.source.LocalBookmarkDataSource
import com.marvel.core.database.characters.source.LocalCharacterDataSource
import com.marvel.core.remote.characters.mapper.CharacterDetailsDomainMapper
import com.marvel.core.remote.characters.mapper.CharacterListDomainMapper
import com.marvel.core.remote.characters.source.RemoteCharacterDataSource
import com.marvel.core.repository.BookmarkListRepositoryImpl
import com.marvel.core.repository.DefaultCharacterDetailsRepository
import com.marvel.core.repository.DefaultCharacterListRepository
import com.marvel.core.usecase.GetHashForRemoteServiceUseCase
import com.marvel.home.repository.BookmarksRepository
import com.marvel.home.repository.CharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterListRepository(
        remoteCharacterDataSource: RemoteCharacterDataSource,
        localCharacterDataSource: LocalCharacterDataSource,
        getHashForRemoteServiceUseCase: GetHashForRemoteServiceUseCase,
        errorMessageMapper: ErrorMessageMapper,
        characterListDomainMapper: CharacterListDomainMapper
    ): CharacterListRepository = DefaultCharacterListRepository(
        remoteCharacterDataSource,
        localCharacterDataSource,
        getHashForRemoteServiceUseCase,
        errorMessageMapper,
        characterListDomainMapper
    )

    @Singleton
    @Provides
    fun provideCharacterDetailsRepository(
        localCharacterDataSource: LocalCharacterDataSource,
        characterDetailsDomainMapper: CharacterDetailsDomainMapper
    ): CharacterDetailsRepository = DefaultCharacterDetailsRepository(
        localCharacterDataSource,
        characterDetailsDomainMapper
    )

    @Singleton
    @Provides
    fun provideBookmarkListRepository(
        localBookmarkDataSource: LocalBookmarkDataSource
    ): BookmarksRepository = BookmarkListRepositoryImpl(
        localBookmarkDataSource
    )
}
