package io.nexoquad.flangify.ui.screens.services

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.nexoquad.flangify.R
import io.nexoquad.flangify.ui.common.components.InsetsTopBar

@Composable
fun ServicesScreen() {
    Scaffold(topBar = {
        InsetsTopBar(
            title = {
                Text(text = stringResource(id = R.string.title_services))
            }, modifier = Modifier.fillMaxWidth()
        )
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

        }
    }
}