package dev.davidodari.androidtmdb.data

import com.google.common.truth.Truth
import dev.davidodari.androidtmdb.core.ErrorType
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.data.movies.DefaultMoviesRepository
import dev.davidodari.androidtmdb.data.movies.remote.DefaultRemoteDataSource
import dev.davidodari.androidtmdb.data.movies.remote.api.MoviesApiService
import dev.davidodari.androidtmdb.data.movies.remote.api.RemoteDataSource
import dev.davidodari.androidtmdb.data.movies.remote.utils.RequestParametersProvider
import dev.davidodari.androidtmdb.fakeSuccessMovieResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.data.movies.remote.utils.MoviesPaginationStore
import dev.davidodari.androidtmdb.fakeSuccessMappedResponse
import dev.davidodari.androidtmdb.movie1
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesRepositoryTest {

    @MockK
    val mockMoviesApiService = mockk<MoviesApiService>()

    // region Latest Movies

    @Test
    fun `when we fetch latest movies,then we get a list of movies`() = runTest {
        // Given
        coEvery {
            mockMoviesApiService.getLatestMovies(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns Response.success(fakeSuccessMovieResponse)

        val remoteDataSource = createRemoteDataSource(
            moviesApiService = mockMoviesApiService,
            requestParametersProvider = RequestParametersProvider()
        )

        val moviesRepository = createMoviesRepository(
            remoteDataSource = remoteDataSource
        )

        // When
        val movies = moviesRepository.fetchLatestMovies()

        // Then
        val expectedResults = fakeSuccessMappedResponse

        Truth.assertThat(movies).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((movies as Result.Success).data).isEqualTo(expectedResults)
    }

    @Test
    fun `when we fetch latest movies and a server error occurs,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockMoviesApiService.getLatestMovies(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Response.error(500, "".toResponseBody())

            val remoteDataSource = createRemoteDataSource(
                moviesApiService = mockMoviesApiService,
                requestParametersProvider = RequestParametersProvider()
            )

            val moviesRepository = createMoviesRepository(
                remoteDataSource = remoteDataSource
            )

            // When
            val movies = moviesRepository.fetchLatestMovies()

            // Then
            Truth.assertThat(movies).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((movies as Result.Error).errorType).isEqualTo(ErrorType.SERVER)
        }

    @Test
    fun `when we fetch latest movies and a client error occurs,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockMoviesApiService.getLatestMovies(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Response.error(406, "".toResponseBody())

            val remoteDataSource = createRemoteDataSource(
                moviesApiService = mockMoviesApiService,
                requestParametersProvider = RequestParametersProvider()
            )

            val moviesRepository = createMoviesRepository(
                remoteDataSource = remoteDataSource
            )

            // When
            val movies = moviesRepository.fetchLatestMovies()

            // Then
            Truth.assertThat(movies).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((movies as Result.Error).errorType).isEqualTo(ErrorType.CLIENT)
        }

    @Test
    fun `when we fetch latest movies and a generic error occurs,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockMoviesApiService.getLatestMovies(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Response.error(605, "".toResponseBody())

            val remoteDataSource = createRemoteDataSource(
                moviesApiService = mockMoviesApiService,
                requestParametersProvider = RequestParametersProvider()
            )

            val moviesRepository = createMoviesRepository(
                remoteDataSource = remoteDataSource
            )

            // When
            val movies = moviesRepository.fetchLatestMovies()

            // Then
            Truth.assertThat(movies).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((movies as Result.Error).errorType).isEqualTo(ErrorType.GENERIC)
        }

    @Test
    fun `when an io exception is thrown,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockMoviesApiService.getLatestMovies(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } throws IOException()

            val remoteDataSource = createRemoteDataSource(
                moviesApiService = mockMoviesApiService,
                requestParametersProvider = RequestParametersProvider()
            )

            val moviesRepository = createMoviesRepository(
                remoteDataSource = remoteDataSource
            )

            // When
            val movies = moviesRepository.fetchLatestMovies()

            // Then
            Truth.assertThat(movies).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((movies as Result.Error).errorType).isEqualTo(ErrorType.IO_CONNECTION)
        }

    @Test
    fun `when we fetch from cache ,then return expected response`() = runTest {
        // Given
        coEvery {
            mockMoviesApiService.getLatestMovies(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns Response.success(fakeSuccessMovieResponse)

        val remoteDataSource = createRemoteDataSource(
            moviesApiService = mockMoviesApiService,
            requestParametersProvider = RequestParametersProvider()
        )

        val moviesRepository = createMoviesRepository(
            remoteDataSource = remoteDataSource
        )

        // When
        val movies = moviesRepository.fetchLatestMovies(fromCache = true)

        // Then
        val expectedCacheResponse = Movies(
            currentPage = 1,
            totalPages = 1,
            movies = emptyList()
        )

        Truth.assertThat(movies).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((movies as Result.Success).data).isEqualTo(expectedCacheResponse)
    }
    // endregion

    // region Helper methods
    private fun createMoviesRepository(
        remoteDataSource: RemoteDataSource
    ): MovieRepository = DefaultMoviesRepository(
        remoteDataSource = remoteDataSource
    )

    private fun createRemoteDataSource(
        moviesApiService: MoviesApiService = mockMoviesApiService,
        requestParametersProvider: RequestParametersProvider = mockk()
    ): RemoteDataSource = DefaultRemoteDataSource(
        moviesApiService = moviesApiService,
        requestParametersProvider = requestParametersProvider
    )

    // endregion
}
