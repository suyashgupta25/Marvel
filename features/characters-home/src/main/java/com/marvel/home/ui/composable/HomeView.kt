package com.marvel.home.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marvel.common.ui.AppTheme
import com.marvel.common.ui.CircularIndeterminateProgressBar
import com.marvel.common.ui.ErrorView
import com.marvel.common.ui.TopAppBarTheme
import com.marvel.home.ui.model.CharacterUiModel
import com.marvel.home.ui.model.HomeUiModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeView(
    uiModel: HomeUiModel,
    scaffoldState: ScaffoldState,
    isRefreshing: Boolean,
    onClick: (Int) -> Unit,
    onBookmarkClick: (Int, Boolean) -> Unit,
    refresh: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { refresh.invoke() })
    when (uiModel) {
        is HomeUiModel.FullScreenError -> ErrorView(message = uiModel.message)
        is HomeUiModel.Loading -> CircularIndeterminateProgressBar()
        is HomeUiModel.Success -> AppTheme {
            Scaffold(scaffoldState = scaffoldState,
                topBar = {
                    TopAppBarTheme(uiModel.toolbarTitle, false)
                }) { padding ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .pullRefresh(pullRefreshState)
                ) {
                    LazyColumn {
                        items(uiModel.characters) { character ->
                            CharacterCard(
                                uiModel = character,
                                onClick = onClick,
                                onBookmarkClick = onBookmarkClick
                            )
                        }
                    }
                    PullRefreshIndicator(
                        refreshing = isRefreshing,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "Home success view")
fun HomePreview() {
    val character = CharacterUiModel(
        id = 345,
        name = "Thor",
        imageUrl = "",
        isBookmarked = true
    )
    val character2 = CharacterUiModel(
        id = 345,
        name = "Black widow",
        imageUrl = "",
        isBookmarked = false
    )
    val model = HomeUiModel.Success("Marvel", listOf(character, character2))
    HomeView(
        uiModel = model,
        scaffoldState = rememberScaffoldState(),
        isRefreshing = false,
        onClick = {},
        onBookmarkClick = { _, _ -> },
        refresh = {})
}

@Composable
@Preview(name = "Home error view", showBackground = true, backgroundColor = 0xFFFFFF)
fun HomeErrorPreview() {
    val model = HomeUiModel.FullScreenError("Marvel", "Cannot load data for now")
    HomeView(
        uiModel = model,
        scaffoldState = rememberScaffoldState(),
        isRefreshing = false,
        onClick = {},
        onBookmarkClick = { _, _ -> },
        refresh = {})
}

@Composable
@Preview(name = "Home loading view", showBackground = true, backgroundColor = 0xFFFFFF)
fun HomeLoadingPreview() {
    val model = HomeUiModel.Loading("Marvel")
    HomeView(
        uiModel = model,
        scaffoldState = rememberScaffoldState(),
        isRefreshing = false,
        onClick = {},
        onBookmarkClick = { _, _ -> },
        refresh = {})
}