package io.nexoquad.flangify.ui.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.nexoquad.flangify.R
import io.nexoquad.flangify.ui.common.components.InsetsTopBar

@Composable
fun HomeScreen(openSettings: () -> Unit) {
    val contextActivity = LocalContext.current as Activity
    val dialogState = remember { mutableStateOf(false) }

    BackHandler {
        dialogState.value = true
    }

    Scaffold(topBar = {
        InsetsTopBar(title = {
            Text(text = stringResource(id = R.string.title_home))
        }, modifier = Modifier.fillMaxWidth(), actions = {
            IconButton(onClick = openSettings) {
                Icon(
                    imageVector = Icons.Default.Settings, contentDescription = stringResource(
                        id = R.string.title_settings
                    )
                )
            }
        })
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

        }
    }

    if (dialogState.value) {
        ExitDialog(onDismiss = {
            dialogState.value = false
        }, onCloseSelected = {
            contextActivity.finish()
        }, dialogState = dialogState)
    }
}

@Composable
fun ExitDialog(
    onDismiss: () -> Unit, onCloseSelected: () -> Unit, dialogState: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.title_dialog_warning),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column() {
                Text(text = stringResource(id = R.string.promt_exit_app))
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.onSurface.copy(0.1f),
                    contentColor = MaterialTheme.colors.primary,
                    disabledBackgroundColor = MaterialTheme.colors.onSurface.copy(0.6f),
                    disabledContentColor = MaterialTheme.colors.onSurface
                ),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)
                        .weight(1f),
                    onClick = { dialogState.value = false }) {
                    Text(text = stringResource(id = R.string.btn_label_no))
                }
                Button(colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.primary,
                    disabledBackgroundColor = MaterialTheme.colors.onSurface.copy(0.8f),
                    disabledContentColor = MaterialTheme.colors.onSurface
                ),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .weight(1f),
                    onClick = {
                        dialogState.value = false
                        onCloseSelected()
                    }) {
                    Text(text = stringResource(id = R.string.btn_label_yes))
                }
            }
        },
        modifier = Modifier
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
            .padding(bottom = 24.dp)
    )
}