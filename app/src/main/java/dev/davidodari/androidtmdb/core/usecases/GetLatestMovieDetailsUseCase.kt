package dev.davidodari.androidtmdb.core.usecases

import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.model.Movie

interface GetLatestMovieDetailsUseCase {

    suspend fun invoke(movieId: Int): Result<Movie>
}
