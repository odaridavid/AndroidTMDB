package dev.davidodari.androidtmdb.features.movie_details

import androidx.annotation.StringRes

data class MovieDetailsScreenState(
    val movieId: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backDropPath: String? = null,
    val releaseDate: String? = null,
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null
) {

    fun onError(@StringRes errorMsg: Int): MovieDetailsScreenState {
        return copy(isLoading = false, errorMsg = errorMsg)
    }

    fun onMovieDetailsLoaded(
        movieId: Int,
        title: String,
        overview: String,
        posterPath: String,
        backdropPath: String,
        releaseDate: String
    ): MovieDetailsScreenState {
        return copy(
            movieId = movieId,
            title = title,
            overview = overview,
            posterPath = posterPath,
            backDropPath = backdropPath,
            releaseDate = releaseDate,
            isLoading = false,
            errorMsg = null
        )
    }
}
