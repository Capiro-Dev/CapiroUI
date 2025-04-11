package com.capiro.composables.dialogs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro
import com.capiro.composables.util_composables.DialogTitle2
import getTypography


/**
 * Displays a dialog with a single positive button and a message. The dialog is shown when [isDialogOpenState] is true.
 *
 * @param imaResource The drawable resource ID for the image to be displayed in the dialog.
 * @param boldText Optional text within [message] that should be bolded.
 * @param message The message text to be displayed in the dialog.
 * @param positiveButtonText The text for the positive button.
 * @param onPositiveButtonClickEvent Callback to be invoked when the positive button is clicked.
 * @param isDialogOpenState Boolean flag to control the visibility of the dialog.
 */
@Composable
fun OneOptionDialogCapiro(
    @DrawableRes imaResource: Int?,
    backgroundColor: Color = WhiteCapiro,
    innerComposable: (@Composable () -> Unit)? = null,
    boldText: String? = null,
    message: String? = null,
    positiveButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
    isDialogOpenState: Boolean
) {
    if (isDialogOpenState) {
        Dialog(onDismissRequest = { /* No action on dismiss */ }) {
            OneOptionDialogLayout(
                backgroundColor = backgroundColor,
                imaResource = imaResource,
                innerComposable = innerComposable,
                boldText = boldText,
                message = message,
                positiveButtonText = positiveButtonText,
                onPositiveButtonClickEvent = onPositiveButtonClickEvent
            )
        }
    }
}

/**
 * Displays the layout of the dialog with a single positive button, an image, and a message. The [boldText] within the [message] will be bolded.
 *
 * @param imaResource The drawable resource ID for the image to be displayed in the dialog.
 * @param boldText Optional text within [message] that should be bolded.
 * @param message The message text to be displayed in the dialog.
 * @param positiveButtonText The text for the positive button.
 * @param onPositiveButtonClickEvent Callback to be invoked when the positive button is clicked.
 */
@Composable
private fun OneOptionDialogLayout(
    @DrawableRes imaResource: Int?,
    boldText: String?,
    message: String?,
    positiveButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
    innerComposable: @Composable() (() -> Unit)?,
    backgroundColor: Color
) {
    // Create annotated string for bold text
    var messageAnnotated = buildAnnotatedString { append(message) }

    if (boldText != null && message != null && message.contains(boldText)) {
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
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (imaResource != null)
            DialogTitle2(imaResource)

        innerComposable?.invoke()
        if (message != null)
            Text(
                text = messageAnnotated,
                color = GreenCapiro,
                style = getTypography().bodyMedium,
                textAlign = TextAlign.Justify
            )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonCapiro(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                text = positiveButtonText,
                onClick = { onPositiveButtonClickEvent() }
            )
        }
    }
}

@Preview
@Composable
private fun OneOptionDialogCapiroPreview() {
    OneOptionDialogCapiro(
        imaResource = R.drawable.flower,
        boldText = "Important",
        message = "This is an important message with a bold text.",
        positiveButtonText = "Ok",
        innerComposable = {
            Column {
                Text(text = "Inner Composable", color = GreenCapiro)
                Text(text = "Inner Composable", color = GreenCapiro)
                Text(text = "Inner Composable", color = GreenCapiro)
                Text(text = "Inner Composable", color = GreenCapiro)
                Text(text = "Inner Composable", color = GreenCapiro)

            }
        },
        onPositiveButtonClickEvent = { /* Handle positive button click */ },
        isDialogOpenState = true,
        backgroundColor = BeigeCapiro
    )
}
