package com.marvel.characterdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.marvel.characterdetails.reducer.CharacterDetailsReducer
import com.marvel.characterdetails.ui.composable.CharacterDetailsView
import com.marvel.characterdetails.ui.middleware.CharacterDetailsDelegate
import com.marvel.characterdetails.ui.model.CharacterDetailsState
import com.marvel.common.tea.holder.LifecycleAwareStoreHolder
import com.marvel.common.tea.screen.ComposeTeaScreen
import com.marvel.common.tea.store.BaseStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class CharacterDetailsFragment : Fragment() {

    @Inject
    lateinit var reducer: CharacterDetailsReducer

    @Inject
    lateinit var middlewareDelegate: CharacterDetailsDelegate

    private val composeTeaScreen =
        ComposeTeaScreen(LifecycleAwareStoreHolder(lifecycle, ::createStore))

    private val inputParamsId by lazy { requireArguments().getInt(CHARACTER_ID, 0) }

    private fun createStore() = BaseStore(CharacterDetailsState.EMPTY, reducer, middlewareDelegate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        val canGoBack = parentFragmentManager.backStackEntryCount >= 1
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        view.setContent {
            val state = composeTeaScreen.states()
            val scaffoldState = rememberScaffoldState()
            CharacterDetailsView(
                uiModel = state.value.uiModel,
                scaffoldState = scaffoldState,
                canGoBack = canGoBack,
                onBackPressed = ::goBackToPreviousScreen
            )
        }
        return view
    }

    private fun goBackToPreviousScreen() {
        parentFragmentManager.popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeTeaScreen.accept(CharacterDetailsEvent.Ui.Init(characterId = inputParamsId))
    }

    companion object {

        private const val CHARACTER_ID = "character_id"

        fun newInstance(characterId: Int) = CharacterDetailsFragment().apply {
            arguments = bundleOf(
                CHARACTER_ID to characterId
            )
        }
    }
}