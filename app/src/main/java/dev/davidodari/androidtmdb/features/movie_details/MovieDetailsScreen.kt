package dev.davidodari.androidtmdb.features.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.davidodari.androidtmdb.designsystem.theme.Padding
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsBackDrop
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsDescription
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsReleaseDate
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsTitle

// TODO Navigate Back
// TODO Loading and error state
@Composable
fun MovieDetailsScreen(state: MovieDetailsScreenState) {
    Column {
        state.backdropUrl?.let { backDrop ->
            MovieDetailsBackDrop(
                backDropUrl = backDrop,
                modifier = Modifier
                    .height(300.dp)
            )
        }
        state.title?.let { title ->
            MovieDetailsTitle(
                title = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Padding.Medium, vertical = Padding.Small
                    )
            )
        }
        state.overview?.let { overview ->
            MovieDetailsDescription(
                title = overview,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Padding.Medium, vertical = Padding.Small
                    )
            )
        }
        state.releaseDate?.let { releaseDate ->
            MovieDetailsReleaseDate(
                releaseDate = releaseDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Padding.Medium, vertical = Padding.Small
                    )
            )
        }
    }
}
