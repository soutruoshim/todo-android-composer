package com.srhdp.to_docompose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                )
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                            Button(onClick = {
                                onYesClicked()
                            }) {
                                Text(text = "Yes")
                            }
            },
            dismissButton = {
                OutlinedButton(onClick = { closeDialog() }) {
                    Text(text = "No")
                }
            },
            onDismissRequest = {
                  closeDialog()
            }

        )
    }
}