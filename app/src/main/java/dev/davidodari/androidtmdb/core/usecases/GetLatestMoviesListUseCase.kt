package dev.davidodari.androidtmdb.core.usecases

import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.model.Movies

interface GetLatestMoviesListUseCase {

    suspend operator fun invoke(): Result<Movies>
}
