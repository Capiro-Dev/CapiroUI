package com.capiro.composables.athomic_composables.switchcapiro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import getTypography



/**
 * A composable function that displays a switch with an accompanying label.
 *
 * @param text The label text displayed alongside the switch.
 * @param isChecked The current state of the switch (checked or unchecked).
 * @param onCheckedChange Callback triggered when the switch state changes.
 * @param textStyle The style of the label text.
 * @param textColor The color of the label text.
 */
@Composable
fun SwitchCapiro(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    textStyle: TextStyle = getTypography().bodyMedium,
    textColor: Color = GreenCapiro
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            modifier = Modifier.scale(0.80f),
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = GreenSecondCapiro,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray,
                uncheckedBorderColor = Color.Transparent,
            )
        )

        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}
@Preview
@Composable
fun SwitchCapiroPreview() {
    SwitchCapiro(
        text = "Switch",
        isChecked = true,
        onCheckedChange = { /*TODO*/ }
    )
}