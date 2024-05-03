package com.capiro.capiroui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cabin
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.capiroui.theme.WhiteCapiro
import com.capiro.capiroui.util_composables.DialogTitle


@Composable
fun TwoOptionsDialogCapiro(
    iconHeader: ImageVector,
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
    onNegativeButtonClickEvent: () -> Unit,
    isDialogOpenState: Boolean
) {
    if (isDialogOpenState) {
        Dialog(onDismissRequest = { },
            content = {
            TwoButtonsDialogLayout(
                iconHeader = iconHeader,
                title = title,
                message = message,
                positiveButtonText = positiveButtonText,
                negativeButtonText = negativeButtonText,
                onPositiveButtonClickEvent = onPositiveButtonClickEvent,
                onNegativeButtonClickEvent = onNegativeButtonClickEvent
            )
        })
    }
}

@Composable
private fun TwoButtonsDialogLayout(
    iconHeader: ImageVector,
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
    onNegativeButtonClickEvent: () -> Unit
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
        DialogTitle(title, iconHeader)
        Text(text=message, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyLarge)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            ButtonCapiro(text = positiveButtonText, onClick = { onPositiveButtonClickEvent() })
            ButtonCapiro(text = negativeButtonText, onClick = { onNegativeButtonClickEvent() })

        }
    }
}



@Preview
@Composable
private fun TwoOptionsDialogCapiroPreview() {
    TwoOptionsDialogCapiro(
        iconHeader = Icons.Filled.Cabin,
        title = "Title",
        message = "Message",
        positiveButtonText = "Positive",
        negativeButtonText = "Negative",
        onPositiveButtonClickEvent = {},
        onNegativeButtonClickEvent = {},
        isDialogOpenState = true
    )
}