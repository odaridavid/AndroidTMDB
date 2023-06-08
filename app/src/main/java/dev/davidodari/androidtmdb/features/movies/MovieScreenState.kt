package dev.davidodari.androidtmdb.features.movies

import androidx.annotation.StringRes

data class MovieScreenState(
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null
)
