package dev.davidodari.androidtmdb.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.davidodari.androidtmdb.core.api.MovieRepository
import dev.davidodari.androidtmdb.core.model.Movies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import dev.davidodari.androidtmdb.core.Result

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MovieScreenState(isLoading = true))
    val state: StateFlow<MovieScreenState> = _state.asStateFlow()

    init {
        processIntent(MovieScreenIntent.LoadLatestMovies)
    }

    fun processIntent(movieScreenIntent: MovieScreenIntent) {
        when (movieScreenIntent) {
            is MovieScreenIntent.LoadLatestMovies -> {
                viewModelScope.launch {
                    val result = movieRepository.fetchLatestMovies()
                    processResult(result)
                }
            }
        }
    }

    private fun processResult(result: Result<Movies>) {
        when (result) {
            is Result.Success -> {
                val movies = result.data
                setState {
                    copy(
                        //todo fix naming here
                        movies = movies.movies,
                        isLoading = false,
                        errorMsg = null
                    )
                }
            }

            is Result.Error -> {
                setState {
                    copy(
                        isLoading = false,
                        errorMsg = mapErrorTypeToResourceId(result.errorType)
                    )
                }
            }
        }
    }

    private fun setState(stateReducer: MovieScreenState.() -> MovieScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}
