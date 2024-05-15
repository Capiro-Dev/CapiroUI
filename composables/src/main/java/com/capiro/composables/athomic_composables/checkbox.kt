package com.capiro.composables.athomic_composables

import androidx.compose.foundation.layout.Row
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
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro

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

@Preview
@Composable
private fun CheckBoxPreview() {
    var isCheckedState by remember { mutableStateOf(false) }
    CheckBoxCapiro(
        isChecked = isCheckedState,
        onCheckedChange = { isCheckedState = it },
        isEnabled = false
    )
}

// *******************************************
//             CHECK BOX AND TEXT
// *******************************************
@Composable
fun CheckBoxAndTextCapiro(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    scale: Float = 1.1f,
    isEnabled: Boolean = true,
) {

    val textColors = if (isEnabled) GreenCapiro else GrayDarkCapiro

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(
            text = text,
            color = textColors,
            style = MaterialTheme.typography.bodyMedium,

        )

        CheckBoxCapiro(
            isChecked = isChecked,
            onCheckedChange = onCheckedChange,
            scale = scale,
            isEnabled = isEnabled
        )
    }
}


@Preview
@Composable
private fun CheckBoxAndTextPreview() {
    var isCheckedState by remember { mutableStateOf(false) }
    CheckBoxAndTextCapiro(
        isChecked = isCheckedState,
        onCheckedChange = { isCheckedState = it },
        text = "Check me",
    )
}
