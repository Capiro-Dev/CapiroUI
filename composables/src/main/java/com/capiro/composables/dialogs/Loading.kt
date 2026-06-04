package com.capiro.composables.dialogs

import TypographyProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro


/**
 * Displays a loading dialog with a title, a message, and an icon. The dialog is shown when [isDialogOpenState] is true.
 *
 * @param isDialogOpenState Boolean flag to control the visibility of the dialog.
 * @param title The title text to be displayed in the dialog.
 * @param message The message text to be displayed in the dialog.
 */
@Composable
fun LoadingDialog(
    isDialogOpenState: Boolean,
    title: String,
    message: String,
    onClose: () -> Unit
) {
    if (isDialogOpenState) {
        AlertDialog(
            onDismissRequest = { onClose()},
            containerColor = Color.Transparent,
            title = null,
            text = null,
            confirmButton = {
                TwoButtonsDialogLayout(
                    title = title,
                    message = message
                )
            }
        )
    }
}

/**
 * Layout for a dialog with a title, a message, and a loading spinner.
 *
 * @param title The title text to be displayed at the top of the dialog.
 * @param message The message text to be displayed below the loading spinner.
 */
@Composable
fun TwoButtonsDialogLayout(
    title: String,
    message: String,
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .background(color = WhiteCapiro, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, style = TypographyProvider.typography.bodyMedium,
            color = GreenCapiro,
            fontWeight = FontWeight.Bold
            )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CircularProgressIndicator(modifier = Modifier.size(64.dp), color = GreenCapiro)
            Text(
                text = message,
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}



@Preview
@Composable
private fun LoadingDialogPreview() {
    LoadingDialog(
        isDialogOpenState = true,
        title = "Loading",
        message = "Please wait while we process your request.",
        onClose = {}
    )
}