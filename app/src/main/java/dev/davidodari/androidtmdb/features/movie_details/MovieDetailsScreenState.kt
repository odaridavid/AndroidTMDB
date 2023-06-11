package dev.davidodari.androidtmdb.features.movie_details

import androidx.annotation.StringRes

data class MovieDetailsScreenState(
    val movieId: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val posterUrl: String? = null,
    val backdropUrl: String? = null,
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
        posterUrl: String,
        backdropUrl: String,
        releaseDate: String
    ): MovieDetailsScreenState {
        return copy(
            movieId = movieId,
            title = title,
            overview = overview,
            posterUrl = posterUrl,
            backdropUrl = backdropUrl,
            releaseDate = releaseDate,
            isLoading = false,
            errorMsg = null
        )
    }
}
