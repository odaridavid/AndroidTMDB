package dev.davidodari.androidtmdb.features.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.designsystem.theme.Padding
import dev.davidodari.androidtmdb.designsystem.widgets.CircleLoadingIndicator
import dev.davidodari.androidtmdb.designsystem.widgets.ErrorScreen
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsBackDrop
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsDescription
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsReleaseDate
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsTitle

// TODO Navigate Back
// TODO Show poster on the side of title.
// TODO Animate state changes
@Composable
fun MovieDetailsScreen(state: MovieDetailsScreenState, onErrorAction: () -> Unit) {
    if (state.isLoading) {
        CircleLoadingIndicator()
    } else if (state.errorMsg != null) {
        ErrorScreen(errorMsg = state.errorMsg, errorActionTitle = R.string.error_retry) {
            onErrorAction()
        }
    } else {
        MovieDetailsContent(state)
    }

}

@Composable
private fun MovieDetailsContent(state: MovieDetailsScreenState) {
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
