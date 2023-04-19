package com.marvel.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ThemeColors,
        typography = QuickSandTypography,
        shapes = AppShapes
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
fun TopAppBarTheme(
    title: String,
    canGoBack: Boolean,
    onBackPressed: (() -> Unit)? = null
) {
    if (title.isNotEmpty()) {
        TopAppBar(
            title = { Text(text = title, fontSize = 20.sp) },
            navigationIcon = if (canGoBack) {
                {
                    IconButton(onClick = { onBackPressed?.let { it() } }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            } else {
                null
            }
        )
    }
}

private val ThemeColors = lightColors(
    primary = MuPalette300,
    primaryVariant = MuPalette700,
    onPrimary = Color.White,
    secondary = Teal200,
    secondaryVariant = Teal700,
    error = Error,
    onError = Error,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White
)




























