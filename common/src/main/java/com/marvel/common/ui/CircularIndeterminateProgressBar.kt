package com.marvel.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Center a circular indeterminate progress bar.
 */
@Composable
fun CircularIndeterminateProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
@Preview(name = "Circular progress bar", showBackground = true, backgroundColor = 0xFFFFFF)
fun CircularIndeterminateProgressBarPreview() {
    CircularIndeterminateProgressBar()
}



















