package com.capiro.composables.athomic_composables.checkbox

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro
import getTypography

@Composable
fun CheckBoxCapiro(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    scale: Float = 1.1f,
    isEnabled: Boolean = true,
) {
    Checkbox(
        modifier = Modifier.scale(scale),
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        enabled = isEnabled,
        colors = CheckboxDefaults.colors(
            checkedColor = GreenCapiro,
            uncheckedColor = GrayDarkCapiro,
            checkmarkColor = WhiteCapiro,
            disabledCheckedColor = GrayDarkCapiro,
            disabledUncheckedColor = GrayDarkCapiro,
        )
    )
}


// *******************************************
//             CHECK BOX AND TEXT
// *******************************************
@Composable
fun CheckBoxAndTextCapiro(
    isChecked: Boolean,
    onSelectedItemChange: (String) -> Unit,
    text: String,
    scale: Float = 1.1f,
    isEnabled: Boolean = true,
) {

    val textColors = if (isEnabled) GreenCapiro else GrayDarkCapiro

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        CheckBoxCapiro(
            isChecked = isChecked,
            onCheckedChange = {onSelectedItemChange(text)},
            scale = scale,
            isEnabled = isEnabled
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text,
            color = textColors,
            style = getTypography().bodyMedium,
        )
    }
}

