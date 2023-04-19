package com.marvel.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.marvel.common.tea.holder.LifecycleAwareStoreHolder
import com.marvel.common.tea.screen.ComposeTeaScreen
import com.marvel.common.tea.store.BaseStore
import com.marvel.home.reducer.HomeReducer
import com.marvel.home.ui.composable.HomeView
import com.marvel.home.ui.middleware.HomeMiddlewareDelegate
import com.marvel.navigation.NavGuide
import com.marvel.navigation.route.CharacterDetailsRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class HomeFragment : Fragment() {

    @Inject
    lateinit var reducer: HomeReducer

    @Inject
    lateinit var middlewareDelegate: HomeMiddlewareDelegate

    @Inject
    lateinit var navGuide: NavGuide

    private val composeTeaScreen =
        ComposeTeaScreen(LifecycleAwareStoreHolder(lifecycle, ::createStore))

    private val inputParamsId by lazy { requireArguments().getInt(CONTAINER_ID, 0) }

    private fun createStore() = BaseStore(HomeState.EMPTY, reducer, middlewareDelegate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        view.setContent {
            val state = composeTeaScreen.states()
            val snackBarHostState = remember { SnackbarHostState() }
            val scaffoldState = rememberScaffoldState(snackbarHostState = snackBarHostState)
            HomeView(
                uiModel = state.value.uiModel,
                scaffoldState = scaffoldState,
                isRefreshing = state.value.isRefreshing, onClick = { id ->
                    composeTeaScreen.accept(HomeEvent.Ui.OnCharacterClick(id))
                }, onBookmarkClick = { id, isBookmarked ->
                    composeTeaScreen.accept(HomeEvent.Ui.OnBookmarkClick(id, isBookmarked))
                }, refresh = {
                    composeTeaScreen.accept(HomeEvent.Ui.OnRefreshTriggered)
                }
            )
            LaunchedEffect(key1 = state.value.snackBarStatus) {
                if (state.value.snackBarStatus.shouldShow) {
                    snackBarHostState.showSnackbar(state.value.snackBarStatus.message)
                    composeTeaScreen.accept(HomeEvent.Ui.ShownConsecutiveDataError)
                }
            }
            LaunchedEffect(key1 = state.value.characterDetails) {
                if (state.value.characterDetails.shouldShow) {
                    navGuide.navigateTo(
                        CharacterDetailsRoute(
                            containerId = inputParamsId,
                            characterId = state.value.characterDetails.detailsScreenCharacterId
                        )
                    )
                    composeTeaScreen.accept(HomeEvent.Ui.AfterCharacterClicked)
                }
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeTeaScreen.accept(HomeEvent.Ui.Init)
    }

    companion object {

        private const val CONTAINER_ID = "container_id"

        fun newInstance(containerId: Int) = HomeFragment().apply {
            arguments = bundleOf(
                CONTAINER_ID to containerId
            )
        }
    }
}