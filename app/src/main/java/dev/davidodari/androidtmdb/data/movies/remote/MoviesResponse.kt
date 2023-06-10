package dev.davidodari.androidtmdb.data.movies.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(val page: Int, val results: List<MovieResponse>)

@Serializable
data class MovieResponse(
    @SerialName("id") val id: Int,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("release_date") val releaseDate: String
)
