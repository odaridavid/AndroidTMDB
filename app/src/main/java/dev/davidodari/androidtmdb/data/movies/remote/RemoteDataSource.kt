package dev.davidodari.androidtmdb.data.movies.remote

import dev.davidodari.androidtmdb.core.model.Movies

interface RemoteDataSource {

    suspend fun getLatestMovies(): Movies
}
