package dev.davidodari.androidtmdb.designsystem.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CircleLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .padding(8.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            color = color
        )
    }
}
