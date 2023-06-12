package dev.davidodari.androidtmdb.usecases

import com.google.common.truth.Truth
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.usecases.DefaultSearchMoviesByTitleUseCase
import dev.davidodari.androidtmdb.core.usecases.SearchMoviesByTitleUseCase
import dev.davidodari.androidtmdb.fakeSuccessMappedResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchMoviesByTitleUseCaseTest {

    @MockK
    val mockKMovieRepository = mockk<MovieRepository>()

    @Test
    fun `when we search for movies by title, then return success`() = runTest {
        // given
        coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Success(
            fakeSuccessMappedResponse
        )

        val useCase = createSearchMoviesByTitleUseCase(mockKMovieRepository)

        // when
        val result = useCase.invoke(query = "Fake")

        // then
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data.movies).isNotEmpty()

    }

    @Test
    fun `when we search for movies by title and it's not found, then return an empty list`() =
        runTest {
            // given
            coEvery { mockKMovieRepository.fetchLatestMovies() } returns Result.Success(
                fakeSuccessMappedResponse
            )

            val useCase = createSearchMoviesByTitleUseCase(mockKMovieRepository)


            // when
            val result = useCase.invoke(query = "Wrong Title")

            // then
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
            Truth.assertThat((result as Result.Success).data.movies).isEmpty()
        }

    private fun createSearchMoviesByTitleUseCase(
        repository: MovieRepository
    ): SearchMoviesByTitleUseCase {
        return DefaultSearchMoviesByTitleUseCase(moviesRepository = repository)
    }
}
