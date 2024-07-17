package com.capiro.composables.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.EMPTY
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import com.capiro.composables.theme.RedCapiro
import getTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCapiro(
    textInput: String,
    label: String,
    onTextChangeEvent: (String) -> Unit,
    isEnabled: Boolean = true,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val typography = getTypography()




    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textInput,
        enabled = true,
        textStyle = typography.bodyMedium.copy(textAlign = TextAlign.Center),
        singleLine = true,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(GreenCapiro),
        onValueChange = { onTextChangeEvent(it) }
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = textInput,
            innerTextField = innerTextField,
            enabled = true,
            singleLine = true,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithLabel(0.dp, 0.dp, 0.dp, 0.dp),
            visualTransformation = VisualTransformation.None,
            trailingIcon = {
                // arrow icon
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
                    color = if (textInput.isEmpty() || textInput == EMPTY || !isEnabled) GrayDarkCapiro else GreenSecondCapiro,
                    style = typography.labelSmall,
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

    val text = remember { mutableStateOf("Text") }
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