package dev.davidodari.androidtmdb.data.movies.remote

import dev.davidodari.androidtmdb.core.model.Movies
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val openWeatherService: MoviesApiService
) : RemoteDataSource {

    override suspend fun getLatestMovies(): Movies {
        return try {
            // TODO move this elsewhere
            val language = "en-US"
            val page = 1
            // TODO Create sorting option enum
            val sortBy = "popularity.desc"
            // TODO Create date utils for date formatting, start at new year and end at current month + date
            val releaseDateEnd = "2023-06-30"
            val releaseDateStart = "2023-01-31"
            val response = openWeatherService.getLatestMovies(
                language = language,
                page = page,
                sortBy = sortBy,
                releaseDateEnd = releaseDateEnd,
                releaseDateStart = releaseDateStart
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

}
