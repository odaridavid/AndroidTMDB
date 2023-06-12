package dev.davidodari.androidtmdb.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.davidodari.androidtmdb.common.toStringResource
import dev.davidodari.androidtmdb.core.Result
import dev.davidodari.androidtmdb.core.model.Movies
import dev.davidodari.androidtmdb.core.usecases.GetLatestMoviesListUseCase
import dev.davidodari.androidtmdb.core.usecases.SearchMoviesByTitleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getLatestMoviesListUseCase: GetLatestMoviesListUseCase,
    private val searchMoviesByTitleUseCase: SearchMoviesByTitleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesScreenState(isLoading = true))
    val state: StateFlow<MoviesScreenState> = _state.asStateFlow()

    init {
        processIntent(MoviesScreenIntent.LoadLatestMovies)
    }

    fun processIntent(movieScreenIntent: MoviesScreenIntent) {
        when (movieScreenIntent) {
            is MoviesScreenIntent.LoadLatestMovies -> {
                viewModelScope.launch {
                    val result = getLatestMoviesListUseCase()
                    processResult(result)
                }
            }
            is MoviesScreenIntent.SearchMovies -> {
                viewModelScope.launch {
                    val result = searchMoviesByTitleUseCase(movieScreenIntent.query)
                    processResult(result)
                }
            }
        }
    }

    private fun processResult(result: Result<Movies>) {
        when (result) {
            is Result.Success -> {
                val movies = result.data.movies
                setState {
                    onMoviesLoaded(movies = movies)
                }
            }

            is Result.Error -> {
                setState {
                    onError(
                        errorMsg = result.errorType.toStringResource()
                    )
                }
            }
        }
    }

    private fun setState(stateReducer: MoviesScreenState.() -> MoviesScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}
