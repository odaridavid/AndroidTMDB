package dev.davidodari.androidtmdb.data.movies.remote

import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.data.movies.remote.api.MoviesApiService
import dev.davidodari.androidtmdb.data.movies.remote.api.RemoteDataSource
import dev.davidodari.androidtmdb.data.movies.remote.utils.MoviesPaginationStore
import dev.davidodari.androidtmdb.data.movies.remote.utils.RequestParametersProvider
import dev.davidodari.androidtmdb.data.movies.remote.utils.mapResponseCodeToThrowable
import dev.davidodari.androidtmdb.data.movies.remote.utils.toDomainModel
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val moviesApiService: MoviesApiService,
    private val requestParametersProvider: RequestParametersProvider
) : RemoteDataSource {


    override suspend fun getLatestMovies(fromCache: Boolean): Movies {
        val currentPage = requestParametersProvider.getCurrentPage()

        if (fromCache) {
            val movies = MoviesPaginationStore.getMovies()
            return Movies(
                movies = movies,
                currentPage = currentPage,
                totalPages = requestParametersProvider.totalPages
            )
        }

        val language = requestParametersProvider.getLanguage()
        val sortBy = requestParametersProvider.getSelectedSortOption().value
        val releaseDateRange = requestParametersProvider.getReleaseDateRange()

        val response = moviesApiService.getLatestMovies(
            language = language,
            page = currentPage,
            sortBy = sortBy,
            releaseDateEnd = releaseDateRange.to,
            releaseDateStart = releaseDateRange.from
        )

        val moviesResponse = response.body()

        return if (response.isSuccessful && moviesResponse != null) {
            requestParametersProvider.totalPages = moviesResponse.totalPages

            if (currentPage < moviesResponse.totalPages) {
                requestParametersProvider.incrementCurrentPage()
            }

            val mappedMovies = moviesResponse.toDomainModel()
            MoviesPaginationStore.addMovies(mappedMovies.movies)

            val movies = MoviesPaginationStore.getMovies()

            mappedMovies.copy(movies = movies)
        } else {
            val exception = mapResponseCodeToThrowable(response.code())
            throw exception
        }
    }

}

