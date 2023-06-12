package dev.davidodari.androidtmdb.core.usecases

import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.core.Result

interface SearchMoviesByTitleUseCase {
    suspend operator fun invoke(query: String): Result<Movies>
}
