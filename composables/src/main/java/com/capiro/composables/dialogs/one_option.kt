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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro
import com.capiro.composables.util_composables.DialogTitle2
import getTypography

@Composable
fun OneOptionDialogCapiro(
    @DrawableRes imaResource: Int,
    boldText: String?=null,
    message: String,
    positiveButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
    isDialogOpenState: Boolean
) {


    if (isDialogOpenState) {
        Dialog(onDismissRequest = { },
            content = {
                OneOptionDialogLayout(
                    imaResource = imaResource,
                    boldText=boldText,
                    message = message,
                    positiveButtonText = positiveButtonText,
                    onPositiveButtonClickEvent = onPositiveButtonClickEvent,
                )
            })
    }
}

@Composable
private fun OneOptionDialogLayout(
    @DrawableRes imaResource: Int,
    boldText: String?,
    message:String,
    positiveButtonText: String,
    onPositiveButtonClickEvent: () -> Unit,
) {

    // find the boldtext in the message and make it bold by using anotates string
    var messageAnnotated = buildAnnotatedString { append( message)}

    if (boldText!=null && message.contains(boldText)){

        messageAnnotated= buildAnnotatedString {
            append(message.substring(0, message.indexOf(boldText)))

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(boldText)
            }

            append(message.substring(message.indexOf(boldText) + boldText.length, message.length))
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ButtonCapiro(modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth() ,text = positiveButtonText, onClick = { onPositiveButtonClickEvent() })
        }
    }
}
