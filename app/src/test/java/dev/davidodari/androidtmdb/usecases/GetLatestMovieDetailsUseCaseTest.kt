package dev.davidodari.androidtmdb.usecases

import com.google.common.truth.Truth
import dev.davidodari.androidtmdb.core.ErrorType
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.usecases.DefaultGetLatestMovieDetailsUseCase
import dev.davidodari.androidtmdb.core.usecases.GetLatestMovieDetailsUseCase
import dev.davidodari.androidtmdb.fakeSuccessMappedResponse
import dev.davidodari.androidtmdb.movie1
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetLatestMovieDetailsUseCaseTest {

    @MockK
    val mockKMovieRepository = mockk<MovieRepository>()

    @Test
    fun `when we get a specific movies details ,then return success`() = runTest {
        // Given
        coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Success(
            fakeSuccessMappedResponse
        )

        val getLatestMovieDetailsUseCase = createGetLatestMovieDetailsUseCase(mockKMovieRepository)

        // When
        val result = getLatestMovieDetailsUseCase.invoke(movieId = 1)

        // Then
        val expectedResult = movie1

        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(expectedResult)
    }

    @Test
    fun `when we get a specific movies details and it's not found ,then return an error`() =
        runTest {
            // Given
            coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Success(
                fakeSuccessMappedResponse
            )

            // When
            val getLatestMovieDetailsUseCase =
                createGetLatestMovieDetailsUseCase(mockKMovieRepository)

            val result = getLatestMovieDetailsUseCase.invoke(movieId = 4)

            // Then
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).errorType)
                .isEqualTo(ErrorType.MOVIE_NOT_FOUND)
        }

    private fun createGetLatestMovieDetailsUseCase(
        repository: MovieRepository
    ): GetLatestMovieDetailsUseCase {
        return DefaultGetLatestMovieDetailsUseCase(movieRepository = repository)
    }

}
