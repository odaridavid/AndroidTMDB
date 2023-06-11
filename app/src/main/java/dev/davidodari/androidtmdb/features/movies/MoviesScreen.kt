package dev.davidodari.androidtmdb.features.movies

import android.util.Log
import androidx.compose.runtime.Composable
import dev.davidodari.androidtmdb.core.model.Movie

@Composable
fun MoviesScreen(state: MoviesScreenState, onMovieSelected: (Movie) -> Unit) {
    // TODO Implement screen and navigation and search functionality.
    Log.i("Movie","$state")
}
