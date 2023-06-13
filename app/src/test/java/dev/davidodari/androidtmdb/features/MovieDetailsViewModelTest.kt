package dev.davidodari.androidtmdb.features

import app.cash.turbine.test
import com.google.common.truth.Truth
import dev.davidodari.androidtmdb.MainCoroutineRule
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.usecases.DefaultGetLatestMovieDetailsUseCase
import dev.davidodari.androidtmdb.core.usecases.GetLatestMovieDetailsUseCase
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.fakeSuccessMappedResponse
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsScreenIntent
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {


    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `when we init the viewmodel, then display loading state`() = runTest {
        // Given
        val repository = mockk<MovieRepository>() {
            coEvery { fetchLatestMovies() } returns Result.Success(fakeSuccessMappedResponse)
        }
        val useCase = DefaultGetLatestMovieDetailsUseCase(movieRepository = repository)

        // When
        val viewModel = createViewModel(
            getLatestMovieDetailsUseCase = useCase
        )

        // Then
        val expectedState = MovieDetailsScreenState(
            isLoading = true
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel with a movie id, then fetch and display the movie`() = runTest {
        // Given
        val repository = mockk<MovieRepository>() {
            coEvery { fetchLatestMovies() } returns Result.Success(fakeSuccessMappedResponse)
        }
        val useCase = DefaultGetLatestMovieDetailsUseCase(movieRepository = repository)

        // When
        val viewModel = createViewModel(
            getLatestMovieDetailsUseCase = useCase
        )

        viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId = 1))

        // Then
        val expectedState = MovieDetailsScreenState(
            isLoading = false,
            errorMsg = null,
            movieId = 1,
            title = "The One",
            overview = "Fake Movie Overview",
            posterUrl = "https://image.tmdb.org/t/p/w185/fake_poster_path",
            backdropUrl = "https://image.tmdb.org/t/p/w780/fake_backdrop_path",
            releaseDate = "2021-01-01"
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }


    @Test
    fun `when we init the viewmodel with a wrong movie id, then display the error screen`() = runTest {
        // Given
        val repository = mockk<MovieRepository>() {
            coEvery { fetchLatestMovies() } returns Result.Success(fakeSuccessMappedResponse)
        }
        val useCase = DefaultGetLatestMovieDetailsUseCase(movieRepository = repository)

        // When
        val viewModel = createViewModel(
            getLatestMovieDetailsUseCase = useCase
        )

        viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId = 5))

        // Then
        val expectedState = MovieDetailsScreenState(
            errorMsg = R.string.error_movie_not_found
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    // region Helper Methods

    private fun createViewModel(
        getLatestMovieDetailsUseCase: GetLatestMovieDetailsUseCase
    ) = MovieDetailsViewModel(
        getLatestMovieDetailsUseCase = getLatestMovieDetailsUseCase
    )

    // endregion
}
