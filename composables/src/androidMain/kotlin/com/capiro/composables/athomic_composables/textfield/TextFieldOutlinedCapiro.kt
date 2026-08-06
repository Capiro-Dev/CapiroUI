package com.capiro.composables.athomic_composables.textfield


import TypographyProvider
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldOutlinedCapiro(
    textInput: String,
    label: String,
    onTextChangeEvent: (String) -> Unit,
    isEnabled: Boolean = true,
    isPassword: Boolean = false,
    isNumeric: Boolean = false,
    iconStart: ImageVector? = null,
    iconDisable: ImageVector? = Icons.Filled.Lock,
    backgroundColor: Color = WhiteCapiro,
    backgroundColorDisabled: Color = GrayClearCapiro,
    borderColor: Color = GreenCapiro,
    borderColorDisabled: Color = GrayDarkCapiro,
    fontColor: Color = GreenSecondCapiro,
    fontColorDisabled: Color = GrayDarkCapiro,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    // Set keyboard options based on input type
    val keyBoardOptions = when {
        isNumeric -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        isPassword && !isPasswordVisible -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        else -> KeyboardOptions.Default
    }

    val backgroundColorSelected = if (!isEnabled) backgroundColorDisabled else backgroundColor
    val borderColorSelected = if (!isEnabled || textInput.isEmpty()) borderColorDisabled else borderColor
    val textColorSelected = if (textInput.isEmpty() || !isEnabled) fontColorDisabled else fontColor

    val interactionSource = remember { MutableInteractionSource() }
    val typography = TypographyProvider.typography

    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textInput,
        enabled = isEnabled,
        visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = typography.bodyMedium.copy(textAlign = TextAlign.Center),
        singleLine = true,
        keyboardOptions = keyBoardOptions,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(GreenCapiro),
        onValueChange = { onTextChangeEvent(it) },
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = textInput,
            innerTextField = innerTextField,
            enabled = isEnabled,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = backgroundColorSelected,
                unfocusedContainerColor = backgroundColorSelected,
                disabledContainerColor = backgroundColorSelected,
                focusedBorderColor = borderColorSelected,
                unfocusedBorderColor = borderColorSelected,
                disabledBorderColor = borderColorDisabled,
                focusedLabelColor = textColorSelected,
                unfocusedLabelColor = textColorSelected,
                disabledLabelColor = textColorSelected,
                cursorColor = GreenCapiro,
            ),
            interactionSource = interactionSource,
            visualTransformation = if (isPassword && isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            contentPadding = TextFieldDefaults.contentPaddingWithLabel(0.dp, 0.dp, 0.dp, 0.dp),
            leadingIcon = iconStart?.let {
                { Icon(imageVector = it, contentDescription = null, tint = GreenCapiro, modifier = Modifier.scale(1.2f)) }
            },
            trailingIcon = {
                when {
                    !isEnabled -> iconDisable?.let {
                        Icon(imageVector = it, contentDescription = null, tint = RedCapiro, modifier = Modifier.scale(1.1f))
                    }
                    isPassword -> {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            tint = GreenCapiro,
                            modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                        )
                    }
                    else -> Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null, tint = GreenCapiro, modifier = Modifier.scale(1.1f))
                }
            },
            label = {
                Text(
                    text = label,
                    color = if (textInput.isEmpty() || !isEnabled) GrayDarkCapiro else GreenSecondCapiro,
                    style = typography.labelSmall,
                )
            },
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = isEnabled,
                    isError = false,
                    interactionSource = interactionSource,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = backgroundColorSelected,
                        unfocusedContainerColor = backgroundColorSelected,
                        disabledContainerColor = backgroundColorSelected,
                        focusedBorderColor = borderColorSelected,
                        unfocusedBorderColor = borderColorSelected,
                        disabledBorderColor = borderColorDisabled,
                        focusedLabelColor = textColorSelected,
                        unfocusedLabelColor = textColorSelected,
                        disabledLabelColor = textColorSelected,
                    ),
                    focusedBorderThickness = 2.dp,
                    unfocusedBorderThickness = 2.dp
                )
            }
        )
    }
}

@Preview
@Composable
private fun TextFieldOutlinedCapiroPreview() {
    val text = remember { mutableStateOf("Text") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextFieldOutlinedCapiro(
            textInput = text.value,
            label = "Label",
            onTextChangeEvent = { text.value = it },
            isEnabled = true
        )
    }
}
