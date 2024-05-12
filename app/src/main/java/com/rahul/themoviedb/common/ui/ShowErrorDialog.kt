package com.rahul.themoviedb.common.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun ShowErrorDialog(
    showDialog: MutableState<Boolean>,
    errorMessage: MutableState<String>,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Error") },
            text = { Text(text = errorMessage.value) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog.value = false
                    onRetry.invoke()
                }) {
                    Text("Retry")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog.value = false
                    onDismiss.invoke()
                }) {
                    Text("Dismiss")
                }
            }
        )
    }
}