package dev.davidodari.androidtmdb.features.movie_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.davidodari.androidtmdb.R
import dev.davidodari.androidtmdb.designsystem.theme.Padding
import dev.davidodari.androidtmdb.designsystem.widgets.CircleLoadingIndicator
import dev.davidodari.androidtmdb.designsystem.widgets.ErrorScreen
import dev.davidodari.androidtmdb.designsystem.widgets.LoadingScreen
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsBackDrop
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsDescription
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsReleaseDate
import dev.davidodari.androidtmdb.designsystem.widgets.MovieDetailsTitle
import dev.davidodari.androidtmdb.designsystem.widgets.TopBar

// TODO Show poster on the side of title.
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieDetailsScreen(
    state: MovieDetailsScreenState,
    onErrorAction: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(onBackPressed = onBackPressed)
        }
    ) { padding ->
        AnimatedContent(targetState = state, label = "Movie Detail Animate") {state->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                if (state.isLoading) {
                    LoadingScreen()
                } else if (state.errorMsg != null) {
                    ErrorScreen(errorMsg = state.errorMsg, errorActionTitle = R.string.error_retry) {
                        onErrorAction()
                    }
                } else {
                    MovieDetailsContent(state)
                }
            }
        }
    }
}

@Composable
private fun MovieDetailsContent(state: MovieDetailsScreenState) {
    state.backdropUrl?.let { backDrop ->
        MovieDetailsBackDrop(
            backDropUrl = backDrop,
            modifier = Modifier
                .fillMaxWidth()
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
