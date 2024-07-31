package com.capiro.composables.athomic_composables.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import getTypography


/**
 * A composable function to display a radio button.
 *
 * @param selection The current selected identifier. This value is used to determine if this radio button should be selected.
 * @param identifier The unique identifier for this radio button. This value is used to determine the selection state.
 * @param onClick A lambda function invoked when the radio button is clicked. It receives the identifier of the clicked radio button.
 * @param isEnabled A boolean flag to enable or disable the radio button. Defaults to true.
 *
 * This composable displays a radio button that reflects its selected state based on the [identifier] and [selection].
 * It updates the selection when clicked and respects the enabled/disabled state.
 */
@Composable
fun RadioButtonCapiro(
    selection: String,
    identifier: String,
    onClick: (String) -> Unit,
    isEnabled: Boolean = true
) {
    RadioButton(
        selected = identifier == selection,
        onClick = { onClick(identifier) },
        enabled = isEnabled,
        colors = RadioButtonDefaults.colors(
            selectedColor = GreenSecondCapiro,
            unselectedColor = GrayDarkCapiro,
            disabledSelectedColor = GrayDarkCapiro,
            disabledUnselectedColor = GrayDarkCapiro
        )
    )
}

/**
 * A composable function to display a radio button with accompanying text.
 *
 * @param selection The current selected identifier. This value is used to determine if this radio button should be selected.
 * @param identifier The unique identifier for this radio button. This value is used to determine the selection state.
 * @param onClick A lambda function invoked when the radio button is clicked. It receives the identifier of the clicked radio button.
 * @param isEnabled A boolean flag to enable or disable the radio button. Defaults to true.
 *
 * This composable displays a radio button along with a text label. It reflects the selected state of the radio button and
 * updates the selection when clicked, while also supporting enabling and disabling of the radio button.
 */
@Composable
fun RadioButtonTextCapiro(
    selection: String,
    identifier: String,
    onClick: (String) -> Unit,
    isEnabled: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButtonCapiro(
            selection = selection,
            identifier = identifier,
            onClick = onClick,
            isEnabled = isEnabled
        )
        Text(
            text = identifier,
            color = GreenCapiro,
            style = getTypography().bodyMedium
        )
    }
}

/**
 * Preview composable for [RadioButtonTextCapiro] to visualize different states of radio buttons.
 */
@Preview
@Composable
private fun RadioButtonPreview() {
    var selection by remember { mutableStateOf("Option 1") }

    Column {
        RadioButtonTextCapiro(
            selection = selection,
            identifier = "Option 1",
            onClick = { selection = it },
            isEnabled = false
        )

        RadioButtonTextCapiro(
            selection = selection,
            identifier = "Option 2",
            onClick = { selection = it },
            isEnabled = true
        )

        RadioButtonTextCapiro(
            selection = selection,
            identifier = "Option 3",
            onClick = { selection = it },
            isEnabled = true
        )
    }
}