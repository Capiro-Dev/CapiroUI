package com.capiro.composables.switchcapiro

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
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import getTypography


@Composable
fun SwitchCapiro(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    textStyle: TextStyle = getTypography().bodyMedium,
    textColor: Color = GreenCapiro
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically){

        Switch(
            modifier = Modifier.scale(0.80f),
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White, // Set to white
                checkedTrackColor = GreenSecondCapiro,
                uncheckedThumbColor = Color.White, // Set to white
                uncheckedTrackColor = Color.LightGray,
                uncheckedBorderColor = Color.Transparent,
            )
        )

        Text(text = text, style = textStyle, color = textColor)

    }



}