package dev.davidodari.androidtmdb.data.movies.remote.api

import dev.davidodari.androidtmdb.core.model.Movies

interface RemoteDataSource {

    suspend fun getLatestMovies(fromCache:Boolean): Movies
}
