package dev.davidodari.androidtmdb.data.movies.remote

import android.util.Log
import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.data.movies.remote.models.ReleaseDateRange
import dev.davidodari.androidtmdb.data.movies.remote.models.ReleaseDateRangeFilterGenerator
import dev.davidodari.androidtmdb.data.movies.remote.models.SortOptions
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val openWeatherService: MoviesApiService
) : RemoteDataSource {

    override suspend fun getLatestMovies(): Movies {
        return try {
            // TODO make this part of user preference
            val language = "en-US"
            val sortBy = SortOptions.POPULARITY.value
            val page = 1
            val releaseDateRange = ReleaseDateRangeFilterGenerator.generateReleaseDateRange()

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
}
