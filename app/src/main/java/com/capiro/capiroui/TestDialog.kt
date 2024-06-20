package com.capiro.capiroui

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.dialogs.TwoOptionsDialogCapiro

@Preview(showBackground = true)
@Composable
fun TestDialog() {

    var isTheDialogOpen by remember { mutableStateOf(false) }
    val context= LocalContext.current

    ButtonCapiro(text = "Two Options", onClick = { isTheDialogOpen= !isTheDialogOpen})


    TwoOptionsDialogCapiro(
        imaResource = R.drawable.siembralogo,
        boldText = "dolor",
        message = "message Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam",
        positiveButtonText = "Positive",
        negativeButtonText = "Negative",
        onPositiveButtonClickEvent = { Toast.makeText(context, "Positive", Toast.LENGTH_SHORT).show()},
        onNegativeButtonClickEvent = {Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show()},
        isDialogOpenState = isTheDialogOpen
    )
}