package com.marvel.common.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.marvel.common.R

@Composable
fun ImageWithAsyncState(
    modifier: Modifier,
    url: String,
    contentScale: ContentScale,
    @DrawableRes defaultImage: Int = R.drawable.loading
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(defaultImage),
        error = painterResource(defaultImage),
        contentDescription = null,
        contentScale = contentScale
    )
}








