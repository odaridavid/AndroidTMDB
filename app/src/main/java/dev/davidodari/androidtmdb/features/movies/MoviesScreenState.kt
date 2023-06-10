package dev.davidodari.androidtmdb.features.movies

import androidx.annotation.StringRes
import dev.davidodari.androidtmdb.core.model.Movie

data class MoviesScreenState(
    // todo ui model for movie
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null
) {
    fun onMoviesLoaded(movies: List<Movie>): MoviesScreenState {
        return copy(movies = movies, isLoading = false, errorMsg = null)
    }

    fun onError(@StringRes errorMsg: Int): MoviesScreenState {
        return copy(isLoading = false, errorMsg = errorMsg)
    }
}
