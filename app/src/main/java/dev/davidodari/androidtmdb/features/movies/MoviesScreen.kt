package dev.davidodari.androidtmdb.features.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.core.model.Movie
import dev.davidodari.androidtmdb.designsystem.theme.Padding
import dev.davidodari.androidtmdb.designsystem.widgets.ErrorScreen
import dev.davidodari.androidtmdb.designsystem.widgets.Headline
import dev.davidodari.androidtmdb.designsystem.widgets.InfoText
import dev.davidodari.androidtmdb.designsystem.widgets.LoadingScreen
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListDescription
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListPoster
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListTitle

@Composable
fun MoviesScreen(
    state: MoviesScreenState,
    onMovieSelected: (Movie) -> Unit,
    onErrorAction: () -> Unit,
    onLoadMore: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Headline(
            title = stringResource(R.string.latest_movies),
            modifier = Modifier.padding(Padding.Medium)
        )

        if (state.isLoading) {
            LoadingScreen()
        } else if (state.errorMsg != null) {
            Spacer(modifier = Modifier.weight(0.5f))
            ErrorScreen(errorMsg = state.errorMsg, errorActionTitle = R.string.error_retry) {
                onErrorAction()
            }
            Spacer(modifier = Modifier.weight(0.5f))
        } else {
            if (state.movies.isEmpty()) {
                EmptyListScreen()
            } else {
                MovieListContent(state, onMovieSelected, onLoadMore)
            }
        }
    }
}

@Composable
private fun MovieListContent(
    state: MoviesScreenState,
    onMovieSelected: (Movie) -> Unit,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(state.movies) { movie ->
            MovieItem(movie = movie) { selectedMovie ->
                onMovieSelected(selectedMovie)
            }
        }
    }

    val scrollContext = rememberScrollContext(listState)

    if (scrollContext.isBottom) {
        onLoadMore()
    }

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
