package dev.davidodari.androidtmdb.data.movies

import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.data.movies.remote.RemoteDataSource
import dev.davidodari.androidtmdb.data.movies.remote.mapThrowableToErrorType
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun fetchLatestMovies(): Result<Movies> =
        try {
            val latestMovies = remoteDataSource.getLatestMovies()
            Result.Success(latestMovies)
        } catch (e: Exception) {
            val errorType = mapThrowableToErrorType(e)
            Result.Error(errorType)
        }

}
