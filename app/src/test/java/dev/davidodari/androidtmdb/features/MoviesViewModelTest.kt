package dev.davidodari.androidtmdb.features

import app.cash.turbine.test
import com.google.common.truth.Truth
import dev.davidodari.androidtmdb.MainCoroutineRule
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.core.ErrorType
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.usecases.DefaultGetLatestMovieListUseCase
import dev.davidodari.androidtmdb.core.usecases.GetLatestMoviesListUseCase
import dev.davidodari.androidtmdb.features.movies.MoviesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.fakeSuccessMappedResponse
import dev.davidodari.androidtmdb.features.movies.MoviesScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `when we init the viewmodel, then display loading state`() = runTest {
        val movieRepository = mockk<MovieRepository>()

        val useCase = DefaultGetLatestMovieListUseCase(movieRepository = movieRepository)

        val viewModel = createViewModel(
            getLatestMoviesListUseCase = useCase
        )

        val expectedState = MoviesScreenState(
            isLoading = true,
            movies = emptyList(),
            errorMsg = null
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel, then fetch and display the movies`() = runTest {
        val movieRepository = mockk<MovieRepository> {
            coEvery { fetchLatestMovies() } returns Result.Success(fakeSuccessMappedResponse)
        }
        val useCase = DefaultGetLatestMovieListUseCase(movieRepository = movieRepository)

        val viewModel = createViewModel(
            getLatestMoviesListUseCase = useCase
        )

        val expectedState = MoviesScreenState(
            isLoading = false,
            movies = fakeSuccessMappedResponse.movies,
            errorMsg = null
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel and an error occurs, then display the error screen`() = runTest {
        val movieRepository = mockk<MovieRepository> {
            coEvery { fetchLatestMovies() } returns Result.Error(ErrorType.CLIENT)
        }
        val useCase = DefaultGetLatestMovieListUseCase(movieRepository = movieRepository)

        val viewModel = createViewModel(
            getLatestMoviesListUseCase = useCase
        )

        val expectedState = MoviesScreenState(
            isLoading = false,
            movies = emptyList(),
            errorMsg = R.string.error_client
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    // region Helper Methods

    private fun createViewModel(getLatestMoviesListUseCase: GetLatestMoviesListUseCase) =
        MoviesViewModel(
            getLatestMoviesListUseCase = getLatestMoviesListUseCase
        )
    // endregion
}
