package dev.davidodari.androidtmdb.designsystem.widgets

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun MovieListPoster(
    posterUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    Log.i("MovieListPoster", "posterUrl: $posterUrl")
    AsyncImage(
        model = posterUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}
