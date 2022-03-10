package io.nexoquad.flangify.ui.screens.home.settings.feedback

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.nexoquad.flangify.R
import io.nexoquad.flangify.ui.common.components.InsetsNestedTopBar

@Composable
fun FeedbackScreen(onBackPressed: () -> Unit){
    Scaffold(topBar = {
        InsetsNestedTopBar(title = {
            Text(text = stringResource(id = R.string.title_feedback))
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
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Do nothing")
            }
        }
    }
}