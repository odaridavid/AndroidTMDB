package dev.davidodari.androidtmdb.data.movies.remote

import dev.davidodari.androidtmdb.data.movies.remote.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("discover/movie")
    suspend fun getLatestMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String,
        @Query("release_date.lte") releaseDateEnd: String,
        @Query("release_date.gte") releaseDateStart: String
    ): Response<MoviesResponse>

}
