package dev.davidodari.androidtmdb.data.movies.remote

import dev.davidodari.androidtmdb.core.ErrorType
import dev.davidodari.androidtmdb.core.model.ClientException
import dev.davidodari.androidtmdb.core.model.GenericException
import dev.davidodari.androidtmdb.core.model.Movie
import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.core.model.ServerException
import dev.davidodari.androidtmdb.core.model.UnauthorizedException
import dev.davidodari.androidtmdb.data.ApiConfigs
import java.io.IOException
import java.net.HttpURLConnection


fun MoviesResponse.toDomainModel(): Movies =
    Movies(
        currentPage = page,
        movies = results.map { it.toDomainModel() }
    )

fun MovieResponse.toDomainModel(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = "${ApiConfigs.Images.BASE_URL}${ApiConfigs.Images.IMAGE_SIZE_W185}$posterPath",
    backdropUrl = "${ApiConfigs.Images.BASE_URL}${ApiConfigs.Images.IMAGE_SIZE_W780}$backdropPath",
    releaseDate = releaseDate
)

fun mapResponseCodeToThrowable(code: Int): Throwable = when (code) {
    HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedException("Unauthorized access : $code")
    in 400..499 -> ClientException("Client error : $code")
    in 500..600 -> ServerException("Server error : $code")
    else -> GenericException("Generic error : $code")
}

fun mapThrowableToErrorType(throwable: Throwable): ErrorType {
    val errorType = when (throwable) {
        is IOException -> ErrorType.IO_CONNECTION
        is ClientException -> ErrorType.CLIENT
        is ServerException -> ErrorType.SERVER
        is UnauthorizedException -> ErrorType.UNAUTHORIZED
        else -> ErrorType.GENERIC
    }
    return errorType
}
