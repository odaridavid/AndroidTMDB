package dev.davidodari.androidtmdb.designsystem.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.davidodari.androidtmdb.designsystem.theme.Padding


@Composable
fun ErrorScreen(
    @StringRes errorMsg: Int,
    @StringRes errorActionTitle: Int,
    onErrorActionClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = errorMsg),
            modifier = Modifier
                .padding(Padding.Medium)
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { onErrorActionClicked() },
            modifier = Modifier
                .padding(Padding.Medium)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = errorActionTitle))
        }
    }
}
