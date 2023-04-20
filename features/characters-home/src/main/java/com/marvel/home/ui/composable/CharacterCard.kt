package com.marvel.home.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marvel.common.util.ImageWithAsyncState
import com.marvel.home.ui.model.CharacterUiModel
import com.marvel.common.R.drawable.img_bookmark as bookmark
import com.marvel.common.R.drawable.img_bookmarked as bookmarked

@Composable
fun CharacterCard(
    uiModel: CharacterUiModel,
    onClick: (Int) -> Unit,
    onBookmarkClick: (Int, Boolean) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = { onClick.invoke(uiModel.id) }),
        elevation = 8.dp,
    ) {
        Column {
            ImageWithAsyncState(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp),
                url = uiModel.imageUrl,
                contentScale = ContentScale.Inside
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = uiModel.name,
                    modifier = Modifier
                        .weight(1.0f)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.h3,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                BookmarkIcon(uiModel.id, uiModel.isBookmarked, onBookmarkClick)
            }
        }
    }
}

@Composable
private fun BookmarkIcon(
    characterId: Int,
    isBookmarked: Boolean,
    onBookmarkClick: (Int, Boolean) -> Unit
) {
    IconButton(
        onClick = { onBookmarkClick.invoke(characterId, isBookmarked) }
    ) {
        Icon(
            modifier = Modifier.padding(10.dp),
            painter = painterResource(
                id = if (isBookmarked)
                    bookmarked
                else
                    bookmark
            ),
            contentDescription = null
        )
    }
}

@Composable
@Preview(name = "Character card preview")
fun CharacterCardPreview() {
    CharacterCard(uiModel = CharacterUiModel(
        id = 345,
        name = "Thor",
        imageUrl = "",
        isBookmarked = true
    ), {
    }, { _, _ ->
    })
}

