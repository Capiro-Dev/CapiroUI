package com.capiro.capiroui.dialogs

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.capiroui.theme.GreenCapiro
import com.capiro.capiroui.theme.WhiteCapiro
import com.capiro.capiroui.util_composables.DialogTitle


@Composable
fun LoadingDialog(
    isDialogOpenState: Boolean,
    title: String,
    message: String,
    icon: ImageVector
) {
    if (isDialogOpenState) {
        AlertDialog(
            onDismissRequest = { },
            containerColor = Color.Transparent,
            title = null,
            text = null,
            confirmButton = {
                TwoButtonsDialogLayout(
                    title = title,
                    message = message,
                    icon = icon
                )
            })
    }

}

@Composable
fun TwoButtonsDialogLayout(
    title: String,
    message: String,
    icon: ImageVector

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
        DialogTitle(title, icon)
        Column (modifier=Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
        icon = Icons.Filled.Sync,
        isDialogOpenState = true,
        title = "Title",
        message = "Message")
}