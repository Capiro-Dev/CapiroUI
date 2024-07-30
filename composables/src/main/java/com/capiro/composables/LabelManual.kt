package com.capiro.composables

import TypographyProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.athomic_composables.CardCapiro
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GreenCapiro

/**
 * A composable function that displays a label with manual input fields and dropdowns for "A", "Y", "W", and a consecutive field.
 *
 * @param textValueA The text value for the "A" field.
 * @param textValueY The text value for the "Y" field.
 * @param textValueW The text value for the "W" field.
 * @param textConsecutive The text value for the consecutive field.
 * @param onTextChangeA Callback to be invoked when the text in the "A" field changes.
 * @param onTextChangeY Callback to be invoked when the text in the "Y" field changes.
 * @param onTextChangeW Callback to be invoked when the text in the "W" field changes.
 * @param itemsY List of items for the "Y" dropdown.
 * @param itemsW List of items for the "W" dropdown.
 * @param onTextChangeConsecutive Callback to be invoked when the text in the consecutive field changes.
 */
@Composable
fun LabelManualCapiro(
    textValueA: String,
    textValueY: String,
    textValueW: String,
    textConsecutive: String,
    onTextChangeA: (String) -> Unit,
    onTextChangeY: (String) -> Unit,
    onTextChangeW: (String) -> Unit,
    itemsY: List<String>,
    itemsW: List<String>,
    onTextChangeConsecutive: (String) -> Unit,
) {
    CardCapiro(innerComposable = {
        Row {
            // A
            TextLabelUnderlined(
                title = "A",
                textValue = textValueA,
                onTextChange = onTextChangeA,
                textLimit = 2
            )

            // Y
            LabelManualDropDownSelection(
                fieldName = "Y",
                items = itemsY,
                selectedItem = textValueY,
                onItemSelectedChange = onTextChangeY
            )

            // W
            LabelManualDropDownSelection(
                fieldName = "W",
                items = itemsW,
                selectedItem = textValueW,
                onItemSelectedChange = onTextChangeW
            )

            // Consecutive
            TextLabelUnderlined(
                title = "-",
                textValue = textConsecutive,
                onTextChange = onTextChangeConsecutive,
                modifier = Modifier.width(80.dp),
                textLimit = 5
            )
        }
    })
}

/**
 * A composable function that displays a label with manual input fields and dropdowns for "A", "Y", "W", "F", and a consecutive field.
 *
 * @param textValueA The text value for the "A" field.
 * @param textValueY The text value for the "Y" field.
 * @param textValueW The text value for the "W" field.
 * @param textValueF The text value for the "F" field.
 * @param textConsecutive The text value for the consecutive field.
 * @param onTextChangeA Callback to be invoked when the text in the "A" field changes.
 * @param onTextChangeY Callback to be invoked when the text in the "Y" field changes.
 * @param onTextChangeW Callback to be invoked when the text in the "W" field changes.
 * @param itemsY List of items for the "Y" dropdown.
 * @param itemsW List of items for the "W" dropdown.
 * @param onTextChangeConsecutive Callback to be invoked when the text in the consecutive field changes.
 */
@Composable
fun LabelManualFarmCapiro(
    textValueA: String,
    textValueY: String,
    textValueW: String,
    textValueF: String,
    textConsecutive: String,
    onTextChangeA: (String) -> Unit,
    onTextChangeY: (String) -> Unit,
    onTextChangeW: (String) -> Unit,
    itemsY: List<String>,
    itemsW: List<String>,
    onTextChangeConsecutive: (String) -> Unit,
) {
    CardCapiro(innerComposable = {
        Row {
            // A
            TextLabelUnderlined(
                title = "A",
                textValue = textValueA,
                onTextChange = onTextChangeA,
                textLimit = 2
            )

            // Y
            LabelManualDropDownSelection(
                fieldName = "Y",
                items = itemsY,
                selectedItem = textValueY,
                onItemSelectedChange = onTextChangeY
            )

            // W
            LabelManualDropDownSelection(
                fieldName = "W",
                items = itemsW,
                selectedItem = textValueW,
                onItemSelectedChange = onTextChangeW
            )

            // F
            TextLabelUnderlined(
                title = "F",
                textValue = textValueF,
                onTextChange = {},
                isEnabled = false,
                textLimit = 2
            )

            // Consecutive
            TextLabelUnderlined(
                title = "-",
                textValue = textConsecutive,
                onTextChange = onTextChangeConsecutive,
                modifier = Modifier.width(80.dp),
                textLimit = 5
            )
        }
    })
}

/**
 * A composable function that displays a text label with an underlined text field.
 *
 * @param title The title or label for the text field.
 * @param textValue The current text value of the text field.
 * @param modifier Modifier for styling and layout adjustments.
 * @param onTextChange Callback to be invoked when the text changes.
 * @param isEnabled Boolean to control if the text field is enabled or not.
 * @param textLimit The maximum number of characters allowed in the text field.
 */
@Composable
private fun TextLabelUnderlined(
    title: String,
    textValue: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit = {},
    isEnabled: Boolean = true,
    textLimit: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = TypographyProvider.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = GreenCapiro,
            fontWeight = FontWeight.Bold
        )

        TextFieldUnderlined(
            text = textValue,
            isEnabled = isEnabled,
            onTextChange = onTextChange,
            modifier = modifier,
            limit = textLimit
        )
    }
}

/**
 * A composable function that displays a dropdown selection for a field.
 *
 * @param fieldName The name of the field for the dropdown.
 * @param items List of items to display in the dropdown menu.
 * @param selectedItem The currently selected item in the dropdown.
 * @param onItemSelectedChange Callback to be invoked when an item is selected.
 */
@Composable
private fun LabelManualDropDownSelection(
    fieldName: String,
    items: List<String>,
    selectedItem: String,
    onItemSelectedChange: (String) -> Unit
) {
    val isExpanded = remember { mutableStateOf(false) }

    Column {
        Row(Modifier.clickable { isExpanded.value = !isExpanded.value }) {
            TextLabelUnderlined(
                title = fieldName,
                textValue = selectedItem,
                isEnabled = false,
                textLimit = 2
            )
        }

        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(
                                text = label,
                                style = TypographyProvider.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = GreenCapiro
                            )
                        }
                    },
                    onClick = {
                        isExpanded.value = false
                        onItemSelectedChange(label)
                    }
                )
            }
        }
    }
}

/**
 * A composable function that displays an underlined text field.
 *
 * @param text The current text value of the text field.
 * @param isEnabled Boolean to control if the text field is enabled or not.
 * @param modifier Modifier for styling and layout adjustments.
 * @param onTextChange Callback to be invoked when the text changes.
 * @param keyboardType The type of keyboard to use for the text field.
 * @param textAlignment The alignment of the text within the text field.
 * @param color The color of the text.
 * @param limit The maximum number of characters allowed in the text field.
 */
@Composable
fun TextFieldUnderlined(
    text: String,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    textAlignment: TextAlign = TextAlign.Center,
    color: Color = GreenCapiro,
    limit: Int = 1000000
) {
    BasicTextField(
        enabled = isEnabled,
        modifier = modifier,
        value = text,
        onValueChange = {
            if (it.length <= limit)
                onTextChange(it)
        },
        textStyle = TypographyProvider.typography.bodyMedium.merge(
            textAlign = textAlignment,
            color = color
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier
                    .width(40.dp)
                    .padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                innerTextField()
                Spacer(modifier = Modifier.height(2.dp))
                Divider(
                    color = Color(0xFF00C853),
                    thickness = 0.8.dp
                )
            }
        }
    )
}


@Preview
@Composable
fun LabelManualPreview() {
    var textValueA by remember { mutableStateOf("0") }
    var textValueY by remember { mutableStateOf("0") }
    var textValueW by remember { mutableStateOf("0") }
    var textConsecutive by remember { mutableStateOf("0") }

    val listItemsY = listOf("01", "02", "3", "4", "5")
    val listItemsW = listOf("01", "02", "3", "4", "5")

    Column(
        Modifier
            .fillMaxSize()
            .background(BeigeCapiro)
            .padding(16.dp)
    ) {
        LabelManualCapiro(
            textValueA = textValueA,
            textValueY = textValueY,
            textValueW = textValueW,
            textConsecutive = textConsecutive,
            onTextChangeA = { textValueA = it },
            onTextChangeY = { textValueY = it },
            onTextChangeW = { textValueW = it },
            itemsY = listItemsY,
            itemsW = listItemsW,
            onTextChangeConsecutive = { textConsecutive = it }
        )

        LabelManualFarmCapiro(
            textValueA = textValueA,
            textValueY = textValueY,
            textValueW = textValueW,
            textValueF = "SS",
            textConsecutive = textConsecutive,
            onTextChangeA = { textValueA = it },
            onTextChangeY = { textValueY = it },
            onTextChangeW = { textValueW = it },
            itemsY = listItemsY,
            itemsW = listItemsW,
            onTextChangeConsecutive = { textConsecutive = it }
        )
    }


}