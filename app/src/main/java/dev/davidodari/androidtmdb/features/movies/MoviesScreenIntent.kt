package dev.davidodari.androidtmdb.features.movies

sealed class MoviesScreenIntent {

    object LoadLatestMovies : MoviesScreenIntent()
}
