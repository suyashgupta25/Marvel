package com.marvel.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(message: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
@Preview(name = "Error view", showBackground = true, backgroundColor = 0xFFFFFF)
fun ErrorViewPreview() {
    ErrorView("Date not found. We regret the inconvenience caused.")
}
















