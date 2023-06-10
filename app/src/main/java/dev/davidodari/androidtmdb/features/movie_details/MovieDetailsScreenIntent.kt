package dev.davidodari.androidtmdb.features.movie_details

sealed class MovieDetailsScreenIntent {

    data class LoadMovieDetail(val movieId: Int) : MovieDetailsScreenIntent()
}
