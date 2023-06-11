package dev.davidodari.androidtmdb.data.movies.remote

import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.data.movies.remote.api.MoviesApiService
import dev.davidodari.androidtmdb.data.movies.remote.api.RemoteDataSource
import dev.davidodari.androidtmdb.data.movies.remote.utils.RequestParametersProvider
import dev.davidodari.androidtmdb.data.movies.remote.utils.mapResponseCodeToThrowable
import dev.davidodari.androidtmdb.data.movies.remote.utils.toDomainModel
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val openWeatherService: MoviesApiService,
    private val requestParametersProvider: RequestParametersProvider
) : RemoteDataSource {

    override suspend fun getLatestMovies(): Movies =
        try {
            val language = requestParametersProvider.getLanguage()
            val sortBy = requestParametersProvider.getSelectedSortOption().value
            val page = requestParametersProvider.getCurrentLastPage()
            val releaseDateRange = requestParametersProvider.getReleaseDateRange()

            val response = openWeatherService.getLatestMovies(
                language = language,
                page = page,
                sortBy = sortBy,
                releaseDateEnd = releaseDateRange.to,
                releaseDateStart = releaseDateRange.from
            )
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.toDomainModel()
            } else {
                val exception = mapResponseCodeToThrowable(response.code())
                throw exception
            }
        } catch (exception: Exception) {
            throw exception
        }

}

