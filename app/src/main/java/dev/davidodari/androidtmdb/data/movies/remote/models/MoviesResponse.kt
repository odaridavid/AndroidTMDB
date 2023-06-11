package dev.davidodari.androidtmdb.data.movies.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MovieResponse>,
    @SerialName("total_pages") val totalPages: Int
)

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
