package dev.davidodari.androidtmdb.data.movies

import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.data.movies.remote.api.RemoteDataSource
import dev.davidodari.androidtmdb.data.movies.remote.utils.toErrorType
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun fetchLatestMovies(fromCache: Boolean): Result<Movies> =
        try {
            val latestMovies = remoteDataSource.getLatestMovies(fromCache = fromCache)
            Result.Success(latestMovies)
        } catch (e: Throwable) {
            Result.Error(e.toErrorType())
        }

}
