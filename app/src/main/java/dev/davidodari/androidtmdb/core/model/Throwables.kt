package dev.davidodari.androidtmdb.core.model

// Throwables that can be sent downstream and give more information about the error
data class ClientException(override val message: String) : Throwable(message = message)

data class ServerException(override val message: String) : Throwable(message = message)

data class UnauthorizedException(override val message: String) : Throwable(message = message)

data class GenericException(override val message: String) : Throwable(message = message)
