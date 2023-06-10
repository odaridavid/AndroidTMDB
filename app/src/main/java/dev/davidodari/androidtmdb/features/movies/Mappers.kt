package dev.davidodari.androidtmdb.features.movies

import androidx.annotation.StringRes
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.core.ErrorType

@StringRes
fun ErrorType.toStringResource(): Int =
    when (this) {
        ErrorType.CLIENT -> R.string.error_client
        ErrorType.SERVER -> R.string.error_server
        ErrorType.IO_CONNECTION -> R.string.error_connection
        ErrorType.GENERIC -> R.string.error_generic
        ErrorType.UNAUTHORIZED -> R.string.error_unauthorized
        ErrorType.MOVIE_NOT_FOUND -> R.string.error_movie_not_found
    }

