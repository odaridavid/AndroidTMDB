package dev.davidodari.androidtmdb.features.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.core.model.Movie
import dev.davidodari.androidtmdb.designsystem.theme.Padding
import dev.davidodari.androidtmdb.designsystem.widgets.CircleLoadingIndicator
import dev.davidodari.androidtmdb.designsystem.widgets.ErrorScreen
import dev.davidodari.androidtmdb.designsystem.widgets.Headline
import dev.davidodari.androidtmdb.designsystem.widgets.InfoText
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListDescription
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListPoster
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListTitle

@Composable
fun MoviesScreen(
    state: MoviesScreenState,
    onMovieSelected: (Movie) -> Unit,
    onErrorAction: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Headline(
            title = stringResource(R.string.latest_movies),
            modifier = Modifier.padding(Padding.Medium)
        )

        // TODO Animate state changes
        if (state.isLoading) {
            LoadingScreen()
        } else if (state.errorMsg != null) {
            ErrorScreen(errorMsg = state.errorMsg, errorActionTitle = R.string.error_retry) {
                onErrorAction()
            }
        } else {
            if (state.movies.isEmpty()) {
                EmptyListScreen()
            } else {
                MovieListContent(state, onMovieSelected)
            }
        }
    }
}

@Composable
private fun MovieListContent(
    state: MoviesScreenState,
    onMovieSelected: (Movie) -> Unit
) {
    LazyColumn {
        items(state.movies) { movie ->
            MovieItem(movie = movie) { selectedMovie ->
                onMovieSelected(selectedMovie)
            }
        }
    }
}

@Composable
private fun ColumnScope.LoadingScreen() {
    Spacer(modifier = Modifier.Companion.weight(0.5f))
    CircleLoadingIndicator(modifier = Modifier.Companion.align(Alignment.CenterHorizontally))
    Spacer(modifier = Modifier.Companion.weight(0.5f))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MovieItem(movie: Movie, onMovieSelected: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding.Small),
        onClick = { onMovieSelected(movie) }
    ) {
        Row {
            MovieListPoster(
                posterUrl = movie.posterUrl,
                modifier = Modifier.padding(Padding.Medium)
            )
            Column {
                MovieListTitle(
                    title = movie.title,
                    modifier = Modifier.padding(top = Padding.Medium)
                )
                MovieListDescription(
                    title = movie.overview,
                    modifier = Modifier.padding(bottom = Padding.Medium, top = Padding.Small)
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.EmptyListScreen() {
    Spacer(modifier = Modifier.weight(0.5f))
    InfoText(
        text = stringResource(R.string.movie_list_no_movies),
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.weight(0.5f))
}
