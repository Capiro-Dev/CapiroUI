package com.capiro.capiroui.athomic_composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.EMPTY
import com.capiro.capiroui.theme.ErrorCapiro
import com.capiro.capiroui.theme.GrayClearCapiro
import com.capiro.capiroui.theme.GrayDarkCapiro
import com.capiro.capiroui.theme.GreenCapiro
import com.capiro.capiroui.theme.GreenSecondCapiro
import com.capiro.capiroui.theme.RedCapiro
import com.capiro.capiroui.theme.WhiteCapiro
import getTypography

//********************************************
//               BUTTON SPINNER
//********************************************
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

    val keyBoardOptions = if (isNumeric) KeyboardOptions(keyboardType = KeyboardType.Decimal)
    else if (isPassword && !isPasswordVisible) KeyboardOptions(keyboardType = KeyboardType.Password)
    else KeyboardOptions.Default


    val backgroundColorSelected = if (!isEnabled) backgroundColorDisabled else backgroundColor
    val borderColorSelected =
        if (!isEnabled || textInput.isEmpty()) borderColorDisabled else borderColor
    val textColorSelected =
        if (textInput.isEmpty() || textInput == EMPTY || !isEnabled) fontColorDisabled else fontColor

    val interactionSource = remember { MutableInteractionSource() }
    val typography = getTypography()




    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textInput,
        enabled = isEnabled,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            contentPadding = TextFieldDefaults.contentPaddingWithLabel(0.dp, 0.dp, 0.dp, 0.dp),
            leadingIcon = {
                // arrow icon
                if (iconStart != null)
                    Icon(
                        imageVector = iconStart,
                        contentDescription = null,
                        tint = GreenCapiro,
                        modifier = Modifier.scale(1.2f)
                    )

            },
            trailingIcon = {
                // arrow icon
                if (!isEnabled && iconDisable != null)
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        tint = RedCapiro,
                        modifier = Modifier.scale(1.1f)
                    )

                if (iconDisable == null)
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint = GreenCapiro,
                        modifier = Modifier.scale(1.1f)
                    )

                if (isPassword)
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = RedCapiro,
                        modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                    )


            },
            label = {
                Text(
                    text = label,
                    color = if (textInput.isEmpty() || textInput == EMPTY || (!isEnabled && iconDisable != null)) GrayDarkCapiro else GreenSecondCapiro,
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
                    focusedBorderThickness = 3.dp,
                    unfocusedBorderThickness = 3.dp
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldCapiro(
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


@Composable
fun TextFieldRoundedBorderCapiro(
    textInput: String,
    onTextChangeEvent: (String) -> Unit,
    isNumeric: Boolean = true,
    errorMessage: String? = null,
    label: String,
    typography: Typography = getTypography(),
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isPassword: Boolean = false
) {

    var isFocused by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }
    val borderColor = if (errorMessage != null) ErrorCapiro else GreenCapiro


    val visualTransformation =
        if (isVisible) PasswordVisualTransformation() else VisualTransformation.None
    val keyboardOptions =
        if (isNumeric) KeyboardOptions(keyboardType = KeyboardType.Number) else if (isPassword) KeyboardOptions(
            keyboardType = KeyboardType.Password
        ) else KeyboardOptions.Default

    Column {

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = GreenCapiro,
                )
            }

            BasicTextField(
                value = textInput,
                textStyle = typography.bodyMedium,
                onValueChange = { onTextChangeEvent(it) },
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,

                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .onFocusChanged { isFocused = it.isFocused },
                decorationBox = { innerTextField ->
                    if (textInput.isEmpty()) {
                        Text(
                            text = label,
                            color = if (isFocused) GreenCapiro else GrayDarkCapiro,
                            style = typography.bodyMedium,
                        )
                    } else {
                        innerTextField()
                    }
                }
            )

            if (trailingIcon != null)
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = GreenCapiro,
                )


            if (isPassword)
                Icon(
                    modifier = Modifier.clickable { isVisible = !isVisible },
                    imageVector = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = null,
                    tint = GreenSecondCapiro,
                )
        }
        Text(modifier=Modifier.height(16.dp).padding(start = 16.dp,top=4.dp), text = errorMessage ?: "", color = ErrorCapiro, style = typography.bodySmall)

    }
}


@Preview
@Composable
private fun TextFieldRoundedBorderPreview() {

    val text = remember { mutableStateOf("Text") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextFieldRoundedBorderCapiro(
            leadingIcon = Icons.Filled.VerifiedUser,
            textInput = text.value,
            onTextChangeEvent = { text.value = it },
            typography = getTypography(),
            isPassword = true,
            isNumeric = false,
            label = "Label"
        )
    }

}

@Composable
fun TextFieldAndTileRoundedBorderCapiro(
    textInput: String,
    onTextChangeEvent: (String) -> Unit,
    isNumeric: Boolean = true,
    label: String,
    title: String,
    errorMessage: String?=null,
    typography: Typography = getTypography(),
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isPassword: Boolean = false

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = title,
            style = typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
            fontWeight = FontWeight.Bold,
            color = GreenCapiro
        )
        TextFieldRoundedBorderCapiro(
            textInput = textInput,
            onTextChangeEvent = onTextChangeEvent,
            isNumeric = isNumeric,
            label = label,
            typography = typography,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isPassword = isPassword,
            errorMessage = errorMessage
        )
    }

}