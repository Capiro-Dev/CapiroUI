package com.capiro.composables.athomic_composables.textfield

import TypographyProvider
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GreenCapiro
import androidx.compose.material3.Typography
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview


/**
 * A composable function that displays a title and a text field with a rounded border.
 *
 * @param textInput The current text input value.
 * @param onTextChangeEvent Callback triggered when the text input changes.
 * @param label The label for the text field.
 * @param title The title displayed above the text field.
 * @param isNumeric Indicates whether the text field is for numeric input.
 * @param errorMessage An optional error message displayed below the text field.
 * @param typography The typography style to use for the text.
 * @param leadingIcon An optional leading icon for the text field.
 * @param trailingIcon An optional trailing icon for the text field.
 * @param isPassword Indicates whether the text field is for password input.
 * @param imeAction The IME action to use for the text field.
 * @param lines The number of lines for the text field.
 */
@Composable
fun TextFieldAndTileRoundedBorderCapiro(
    textInput: String,
    onTextChangeEvent: (String) -> Unit,
    label: String,
    title: String,
    isNumeric: Boolean = false,
    errorMessage: String? = null,
    typography: Typography = TypographyProvider.typography,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    lines: Int = 1,

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = GreenCapiro,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextFieldRoundedBorderCapiro(
            textInput = textInput,
            onTextChangeEvent = onTextChangeEvent,
            label = label,
            isNumeric = isNumeric,
            errorMessage = errorMessage,
            typography = typography,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isPassword = isPassword,
            imeAction = imeAction,
            lines = lines,
        )
    }
}

@Preview
@Composable
fun PreviewTextFieldAndTileRoundedBorderCapiro() {
    TextFieldAndTileRoundedBorderCapiro(
        textInput = "Text",
        onTextChangeEvent = {},
        label = "Label",
        title = "Title"
    )
}