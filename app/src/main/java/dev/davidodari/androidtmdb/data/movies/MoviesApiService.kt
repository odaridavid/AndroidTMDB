package dev.davidodari.androidtmdb.data.movies

import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiService {

    @GET("/data/3.0/onecall")
    suspend fun getLatestMovies(): Response<MoviesResponse>

}
