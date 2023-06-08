package dev.davidodari.androidtmdb.data.movies

import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movie
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val openWeatherService: MoviesApiService
) : MovieRepository {

    override fun fetchLatestMovies(): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

}
