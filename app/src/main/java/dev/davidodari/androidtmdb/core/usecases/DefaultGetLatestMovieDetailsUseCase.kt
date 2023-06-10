package dev.davidodari.androidtmdb.core.usecases

import dev.davidodari.androidtmdb.core.ErrorType
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movie
import javax.inject.Inject

class DefaultGetLatestMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : GetLatestMovieDetailsUseCase {

    override suspend fun invoke(movieId: Int): Result<Movie> {
        return when (val result = movieRepository.fetchLatestMovies()) {
            is Result.Success -> {
                val movie = result.data.movies.find { it.id == movieId }
                if (movie == null) {
                    Result.Error(ErrorType.MOVIE_NOT_FOUND)
                } else {
                    Result.Success(movie)
                }
            }

            is Result.Error -> Result.Error(result.errorType)
        }
    }
}
