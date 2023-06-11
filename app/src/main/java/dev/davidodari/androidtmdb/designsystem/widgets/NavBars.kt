package dev.davidodari.androidtmdb.designsystem.widgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

// TODO Add more actions and items to top bar e.g search and title.
@Composable
fun TopBar(onBackPressed: (() -> Unit)? = null) {
    TopAppBar(
        title = { },
        navigationIcon = {
            BackArrowIcon(
                onClick = { onBackPressed?.invoke() }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun BackArrowIcon(onClick: () -> Unit?) {
    IconButton(
        onClick = { onClick() },
        content = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Arrow"
            )
        }
    )
}

