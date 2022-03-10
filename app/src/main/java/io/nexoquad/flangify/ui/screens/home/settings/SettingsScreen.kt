package io.nexoquad.flangify.ui.screens.home.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.nexoquad.flangify.R
import io.nexoquad.flangify.ui.common.components.InsetsNestedTopBar

@Composable
fun SettingsScreen(
    onBackPressed: () -> Unit,
    openFeedback: () -> Unit,
    openRegulatory: () -> Unit,
    openAboutApp: () -> Unit
) {
    Scaffold(topBar = {
        InsetsNestedTopBar(title = {
            Text(text = stringResource(id = R.string.title_settings))
        }, navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.accessibility_navigate_back),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }, modifier = Modifier.fillMaxWidth()
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = openFeedback, modifier = Modifier.padding(16.dp)) {
                Text(text = "Feedback")
            }
            TextButton(onClick = openRegulatory, modifier = Modifier.padding(16.dp)) {
                Text(text = "Regulatory")
            }
            TextButton(onClick = openAboutApp, modifier = Modifier.padding(16.dp)) {
                Text(text = "About app")
            }
        }
    }
}