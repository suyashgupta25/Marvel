package com.marvel.characterdetails.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marvel.characterdetails.ui.model.CharacterDetailsSuccessUiModel
import com.marvel.characterdetails.ui.model.CharacterDetailsUiModel
import com.marvel.common.ui.AppTheme
import com.marvel.common.ui.ErrorView
import com.marvel.common.ui.TopAppBarTheme
import com.marvel.common.util.ImageWithAsyncState

@Composable
internal fun CharacterDetailsView(
    uiModel: CharacterDetailsUiModel,
    scaffoldState: ScaffoldState,
    canGoBack: Boolean,
    onBackPressed: () -> Unit
) {
    when (uiModel) {
        is CharacterDetailsUiModel.FullScreenError -> ShowErrorView(uiModel = uiModel)
        CharacterDetailsUiModel.Loading -> CircularProgressIndicator()
        is CharacterDetailsUiModel.Success -> CharacterDetailsView(
            successUiModel = uiModel.successUiModel,
            scaffoldState = scaffoldState,
            canGoBack = canGoBack,
            onBackPressed = onBackPressed
        )
    }
}

@Composable
private fun ShowErrorView(uiModel: CharacterDetailsUiModel.FullScreenError) =
    ErrorView(message = uiModel.message)

@Composable
private fun CharacterDetailsView(
    successUiModel: CharacterDetailsSuccessUiModel,
    scaffoldState: ScaffoldState,
    canGoBack: Boolean,
    onBackPressed: () -> Unit
) {
    AppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopAppBarTheme(successUiModel.name, canGoBack, onBackPressed) }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                // default height of the character image for now
                ImageWithAsyncState(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp),
                    url = successUiModel.imageUrl,
                    contentScale = ContentScale.Inside
                )
                Text(
                    text = successUiModel.description,
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = successUiModel.lastUpdated,
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}

@Composable
@Preview(name = "Character details success view")
fun CharacterDetailsSuccessView() {
    val character = CharacterDetailsUiModel.Success(
        CharacterDetailsSuccessUiModel(
            id = 22,
            name = "Thor",
            description = "Thor (from Old Norse: Þórr) is a prominent god in Germanic paganism. In Norse mythology, he is a hammer-wielding god associated with lightning, thunder, storms, sacred groves and trees, strength, the protection of humankind, hallowing, and fertility. Besides Old Norse Þórr, the deity occurs in Old English as Þunor, in Old Frisian as Thuner, in Old Saxon as Thunar, and in Old High German as Donar, all ultimately stemming from the Proto-Germanic theonym *Þun(a)raz, meaning 'Thunder'.",
            lastUpdated = "Last updated: 01 May 2020",
            imageUrl = ""
        )
    )
    CharacterDetailsView(
        character,
        scaffoldState = rememberScaffoldState(), true
    ) {}
}

@Composable
@Preview(name = "Character details success view", showBackground = true, backgroundColor = 0xFFFFFF)
fun CharacterDetailsErrorView() {
    val character = CharacterDetailsUiModel.FullScreenError(
        message = "Cannot load this character for now. Please try again later"
    )
    CharacterDetailsView(
        character,
        scaffoldState = rememberScaffoldState(), true
    ) {}
}

@Composable
@Preview(name = "Character details loading view", showBackground = true, backgroundColor = 0xFFFFFF)
fun CharacterDetailsLoadingView() {
    val character = CharacterDetailsUiModel.Loading
    CharacterDetailsView(
        character,
        scaffoldState = rememberScaffoldState(), true
    ) {}
}