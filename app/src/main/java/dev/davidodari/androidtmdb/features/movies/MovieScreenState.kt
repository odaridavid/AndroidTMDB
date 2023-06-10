package dev.davidodari.androidtmdb.features.movies

import androidx.annotation.StringRes
import dev.davidodari.androidtmdb.core.model.Movie

data class MovieScreenState(
    // todo ui model
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null
)
