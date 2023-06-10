package dev.davidodari.androidtmdb.core.usecases

import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movies
import javax.inject.Inject

class DefaultGetLatestMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : GetLatestMoviesListUseCase {

    override suspend fun invoke(): Result<Movies> {
        TODO("Not yet implemented")
    }
}
