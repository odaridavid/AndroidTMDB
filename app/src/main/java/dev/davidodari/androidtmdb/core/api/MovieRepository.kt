package dev.davidodari.androidtmdb.core.api

import dev.davidodari.androidtmdb.core.model.Movie

interface MovieRepository {

    fun fetchLatestMovies() : Result<List<Movie>>

}
