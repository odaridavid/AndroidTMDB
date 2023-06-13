package dev.davidodari.androidtmdb.core.api

import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.model.Movies

interface MovieRepository {

    suspend fun fetchLatestMovies(fromCache: Boolean = false): Result<Movies>

}
