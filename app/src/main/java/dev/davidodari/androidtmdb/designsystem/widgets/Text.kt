package dev.davidodari.androidtmdb.designsystem.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import dev.davidodari.androidtmdb.R

@Composable
fun Headline(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MovieListTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MovieListDescription(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Normal,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun MovieDetailsTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MovieDetailsDescription(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun MovieDetailsReleaseDate(releaseDate: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.movie_details_release_date, releaseDate),
        modifier = modifier,
        style = MaterialTheme.typography.subtitle2,
        fontWeight = FontWeight.Thin
    )
}
