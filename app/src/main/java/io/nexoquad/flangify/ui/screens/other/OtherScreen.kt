package io.nexoquad.flangify.ui.screens.other

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.nexoquad.flangify.R
import io.nexoquad.flangify.ui.common.components.InsetsTopBar

@Composable
fun OtherScreen(openAppManager: () -> Unit, openUpdateCenter: () -> Unit) {
    Scaffold(topBar = {
        InsetsTopBar(
            title = {
                Text(text = stringResource(id = R.string.title_other))
            }, modifier = Modifier.fillMaxWidth()
        )
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(onClick = openAppManager) {
                Text(text = "App manager")
            }
            Button(onClick = openUpdateCenter) {
                Text(text = "Update center")
            }
        }
    }
}