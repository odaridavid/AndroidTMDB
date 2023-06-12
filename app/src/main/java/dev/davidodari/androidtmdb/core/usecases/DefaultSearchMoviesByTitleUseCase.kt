package dev.davidodari.androidtmdb.core.usecases

import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movies
import javax.inject.Inject
import dev.davidodari.androidtmdb.core.Result

class DefaultSearchMoviesByTitleUseCase @Inject constructor(
    private val moviesRepository: MovieRepository
) : SearchMoviesByTitleUseCase {

    override suspend fun invoke(query: String): Result<Movies> =
        when (val result = moviesRepository.fetchLatestMovies()) {
            is Result.Success -> {
                val movies = result.data
                val filteredMovies = movies.movies.filter { movie ->
                    movie.title.contains(query, ignoreCase = true)
                }
                Result.Success(movies.copy(movies = filteredMovies))
            }

            is Result.Error -> result
        }

}
