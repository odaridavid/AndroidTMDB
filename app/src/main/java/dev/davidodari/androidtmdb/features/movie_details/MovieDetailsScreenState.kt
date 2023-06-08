package dev.davidodari.androidtmdb.features.movie_details

import androidx.annotation.StringRes

data class MovieDetailsScreenState(
    val title:String,
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null
)
