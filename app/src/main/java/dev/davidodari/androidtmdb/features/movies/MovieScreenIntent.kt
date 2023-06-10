package dev.davidodari.androidtmdb.features.movies

sealed class MovieScreenIntent {

    object LoadLatestMovies : MovieScreenIntent()
}
