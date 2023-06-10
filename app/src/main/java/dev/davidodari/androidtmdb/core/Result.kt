package dev.davidodari.androidtmdb.core

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val errorType: ErrorType) : Result<T>()
}

enum class ErrorType {
    CLIENT,
    SERVER,
    GENERIC,
    IO_CONNECTION,
    UNAUTHORIZED,
    MOVIE_NOT_FOUND
}
