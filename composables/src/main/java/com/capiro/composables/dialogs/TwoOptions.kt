package com.capiro.composables.dialogs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.theme.WhiteCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.DialogTitle2
import getTypography



/**
 * Displays a dialog with two buttons and a message. The dialog is shown when [isDialogOpenState] is true.
 *
 * @param imaResource The drawable resource ID for the image to be displayed in the dialog.
 * @param boldText Optional text within [message] that should be bolded.
 * @param message The message text to be displayed in the dialog.
 * @param positiveButtonText The text for the positive button.
 * @param negativeButtonText The text for the negative button.
 * @param onPositiveButtonClickEvent Callback to be invoked when the positive button is clicked.
 * @param onNegativeButtonClickEvent Callback to be invoked when the negative button is clicked.
 * @param isDialogOpenState Boolean flag to control the visibility of the dialog.
 */
@Composable
fun TwoOptionsDialogCapiro(
    @DrawableRes imaResource: Int,
    boldText: String? = null,
    message: String,
    innerComposable: (@Composable ()->Unit)? = null,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
    onNegativeButtonClickEvent: () -> Unit,
    isDialogOpenState: Boolean
) {
    if (isDialogOpenState) {
        Dialog(onDismissRequest = { /* No action on dismiss */ }) {
            TwoButtonsDialogLayout(
                imaResource = imaResource,
                boldText = boldText,
                message = message,
                innerComposable=innerComposable,
                positiveButtonText = positiveButtonText,
                negativeButtonText = negativeButtonText,
                onPositiveButtonClickEvent = onPositiveButtonClickEvent,
                onNegativeButtonClickEvent = onNegativeButtonClickEvent
            )
        }
    }
}

/**
 * Displays the layout of the dialog with two buttons, an image, and a message. The [boldText] within the [message] will be bolded.
 *
 * @param imaResource The drawable resource ID for the image to be displayed in the dialog.
 * @param boldText Optional text within [message] that should be bolded.
 * @param message The message text to be displayed in the dialog.
 * @param positiveButtonText The text for the positive button.
 * @param negativeButtonText The text for the negative button.
 * @param onPositiveButtonClickEvent Callback to be invoked when the positive button is clicked.
 * @param onNegativeButtonClickEvent Callback to be invoked when the negative button is clicked.
 */
@Composable
private fun TwoButtonsDialogLayout(
    @DrawableRes imaResource: Int,
    boldText: String?,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
    onNegativeButtonClickEvent: () -> Unit,
    innerComposable: @Composable() (() -> Unit)?
) {
    // Create annotated string for bold text
    var messageAnnotated = buildAnnotatedString { append(message) }

    if (boldText != null && message.contains(boldText)) {
        messageAnnotated = buildAnnotatedString {
            append(message.substring(0, message.indexOf(boldText)))
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(boldText)
            }
            append(message.substring(message.indexOf(boldText) + boldText.length))
        }
    }

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(color = WhiteCapiro, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DialogTitle2(imaResource)
        Text(
            text = messageAnnotated,
            color = GreenCapiro,
            style = getTypography().bodyMedium,
            textAlign = TextAlign.Justify
        )
        if (innerComposable != null) {
            innerComposable()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonCapiro(text = positiveButtonText, onClick = onPositiveButtonClickEvent)
            ButtonCapiro(text = negativeButtonText, onClick = onNegativeButtonClickEvent)
        }
    }
}

@Preview
@Composable
private fun TwoOptionsDialogCapiroPreview() {
    TwoOptionsDialogCapiro(
        imaResource = R.drawable.flower,
        boldText = "Important",
        message = "This is an important message with a bold text.",
        positiveButtonText = "Yes",
        negativeButtonText = "No",
        onPositiveButtonClickEvent = { /* Handle positive button click */ },
        onNegativeButtonClickEvent = { /* Handle negative button click */ },
        isDialogOpenState = true
    )
}


