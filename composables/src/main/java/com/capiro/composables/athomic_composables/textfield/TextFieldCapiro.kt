package com.capiro.composables.athomic_composables.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.theme.BlackCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.RedCapiro
import getTypography

/**
 * A custom text field with a label and optional trailing icon, designed with rounded borders.
 *
 * @param textInput The current text input value.
 * @param label The label text displayed above the text field.
 * @param onTextChangeEvent Callback triggered when the text input changes.
 * @param isEnabled Whether the text field is enabled for interaction.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCapiro(
    textInput: String,
    label: String,
    onTextChangeEvent: (String) -> Unit,
    isEnabled: Boolean = true,
    isNumeric: Boolean = true,
    isError: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val typography = TypographyProvider.typography
    val keyType = if (isNumeric) KeyboardType.Number else KeyboardType.Text

    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textInput,
        enabled = isEnabled,
        textStyle = typography.bodyMedium.copy(textAlign = TextAlign.Start, color = GreenCapiro, fontWeight = FontWeight.Bold),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyType),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(GreenCapiro),
        onValueChange = onTextChangeEvent
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = textInput,
            innerTextField = innerTextField,
            enabled = isEnabled,
            singleLine = true,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithLabel(0.dp, 0.dp, 0.dp, 0.dp),
            visualTransformation = VisualTransformation.None,
            trailingIcon = {
                if (!isEnabled)
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        tint = RedCapiro,
                        modifier = Modifier.scale(1.1f)
                    )
            },
            label = {
                Text(
                    text = label,
                    style = getTypography().bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (!isError) BlackCapiro else RedCapiro,
                )
            },
            container = {
                TextFieldDefaults.ContainerBox(
                    enabled = isEnabled,
                    isError = false,
                    interactionSource = interactionSource,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GreenCapiro,
                        unfocusedBorderColor = GrayDarkCapiro,
                        disabledBorderColor = GrayDarkCapiro,
                        cursorColor = GreenCapiro
                    )
                )
            }
        )
    }
}

@Preview
@Composable
private fun TextFieldCapiroPreview() {
    val text = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextFieldCapiro(
            textInput = text.value,
            label = "Label",
            onTextChangeEvent = { text.value = it }
        )
    }
}
