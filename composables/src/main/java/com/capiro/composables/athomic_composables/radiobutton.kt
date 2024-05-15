package com.capiro.composables.athomic_composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro

@Composable
fun RadioButtonCapiro(
    selection: String,
    identifier: String,
    onClick: (String) -> Unit,
    isEnabled: Boolean = true,
) {

    RadioButton(
        selected = identifier == selection,
        onClick = { onClick(identifier) },
        enabled = isEnabled,
        colors = RadioButtonDefaults.colors(
            selectedColor = GreenCapiro,
            unselectedColor = GrayDarkCapiro,
            disabledSelectedColor = GrayDarkCapiro,
            disabledUnselectedColor = GrayDarkCapiro,
        )
    )
}


@Composable
fun RadioButtonTextCapiro(
    selection: String,
    identifier: String,
    onClick: (String) -> Unit,
    isEnabled: Boolean = true,

    ) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButtonCapiro(
            selection = selection,
            identifier = identifier,
            onClick = onClick,
            isEnabled = isEnabled
        )
        Text(text = identifier, color = GrayDarkCapiro, style = MaterialTheme.typography.bodyMedium)

    }
}

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