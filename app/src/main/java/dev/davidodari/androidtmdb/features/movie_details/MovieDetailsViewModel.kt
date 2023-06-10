package dev.davidodari.androidtmdb.features.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movie
import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.core.usecases.GetLatestMovieDetailsUseCase
import dev.davidodari.androidtmdb.features.movies.MoviesScreenIntent
import dev.davidodari.androidtmdb.features.movies.MoviesScreenState
import dev.davidodari.androidtmdb.features.movies.mapErrorTypeToResourceId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getLatestMovieDetailsUseCase: GetLatestMovieDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsScreenState(isLoading = true))
    val state: StateFlow<MovieDetailsScreenState> = _state.asStateFlow()

    fun processIntent(movieScreenIntent: MovieDetailsScreenIntent) {
        when (movieScreenIntent) {
            is MovieDetailsScreenIntent.LoadMovieDetail -> {
                viewModelScope.launch {
                    val result = getLatestMovieDetailsUseCase(movieScreenIntent.movieId)
                    processResult(result)
                }
            }
        }
    }

    private fun processResult(result: Result<Movie>) {
        when (result) {
            is Result.Success -> {
                val movie = result.data
                setState {
                    onMovieDetailsLoaded(
                        movieId = movie.id,
                        title = movie.title,
                        overview = movie.overview,
                        posterPath = movie.posterPath,
                        backdropPath = movie.backdropPath,
                        releaseDate = movie.releaseDate
                    )
                }
            }

            is Result.Error -> {
                setState {
                    onError(errorMsg = mapErrorTypeToResourceId(result.errorType))
                }
            }
        }
    }

    private fun setState(stateReducer: MovieDetailsScreenState.() -> MovieDetailsScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}
