package com.marvel.home.ui.middleware

import com.marvel.common.error.ErrorMessageMapper
import com.marvel.common.tea.SimpleMiddleware
import com.marvel.common.util.StringProvider
import com.marvel.home.repository.BookmarksRepository
import com.marvel.home.repository.CharacterListRepository
import com.marvel.home.ui.mapper.HomeUiModelMapper
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject
import com.marvel.home.ui.HomeCommand as Command
import com.marvel.home.ui.HomeEvent as Event

internal class HomeLoadInitialDataMiddleware @Inject constructor(
    private val characterListRepository: CharacterListRepository,
    private val bookmarksRepository: BookmarksRepository,
    private val homeUiModelMapper: HomeUiModelMapper,
    private val errorMessageMapper: ErrorMessageMapper,
    private val stringProvider: StringProvider
) : SimpleMiddleware<Command.LoadInitialData, Event.Internal> {

    override fun execute(command: Command.LoadInitialData): Observable<Event.Internal> =
        Observable.combineLatest(
            characterListRepository.data, //Local + Remote
            bookmarksRepository.data //Local only
        ) { characterList, bookmarkList ->
            if (characterList.isNotEmpty()) {
                Event.Internal.InitialDataLoaded( // show the combined data
                    homeUiModelMapper.toUiModel(
                        characters = characterList,
                        bookmarks = bookmarkList
                    )
                )
            } else {
                Event.Internal.DoNothing
            }
        }.onErrorResumeNext {
            Timber.e(it)
            Observable.just(it.createMainErrorMessageEvent())
        }

    private fun Throwable.createMainErrorMessageEvent(): Event.Internal =
        Event.Internal.InitialDataError(
            toolbarTitle = stringProvider.getHomeScreenTitle(),
            message = errorMessageMapper.apply(this)
        )
}
