package dev.davidodari.androidtmdb.features.movies

import androidx.annotation.StringRes
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.core.ErrorType

@StringRes
fun mapErrorTypeToResourceId(errorType: ErrorType): Int {
    return when (errorType) {
        ErrorType.CLIENT -> R.string.error_client
        ErrorType.SERVER -> R.string.error_server
        ErrorType.IO_CONNECTION -> R.string.error_connection
        ErrorType.GENERIC -> R.string.error_generic
        ErrorType.UNAUTHORIZED -> R.string.error_unauthorized
    }
}
