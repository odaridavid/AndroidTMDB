package dev.davidodari.androidtmdb.features.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.core.model.Movie
import dev.davidodari.androidtmdb.designsystem.theme.Padding
import dev.davidodari.androidtmdb.designsystem.widgets.Headline
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListDescription
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListPoster
import dev.davidodari.androidtmdb.designsystem.widgets.MovieListTitle

@Composable
fun MoviesScreen(state: MoviesScreenState, onMovieSelected: (Movie) -> Unit) {
    Column {
        Headline(
            title = stringResource(R.string.latest_movies),
            modifier = Modifier.padding(Padding.Medium)
        )
        LazyColumn {
            items(state.movies) { movie ->
                MovieItem(movie = movie){ selectedMovie ->
                    onMovieSelected(selectedMovie)}
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(movie: Movie,onMovieSelected: (Movie) -> Unit) {
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
