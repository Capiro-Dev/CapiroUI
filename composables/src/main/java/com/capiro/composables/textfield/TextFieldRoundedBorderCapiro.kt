package com.capiro.composables.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.ErrorCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import getTypography
import androidx.compose.material3.Typography
import androidx.compose.ui.text.input.ImeAction

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
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    lines: Int = 1
) {

    var isFocused by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(isPassword) }
    val borderColor = if (errorMessage != null) ErrorCapiro else GreenCapiro


    val visualTransformation =
        if (isVisible) PasswordVisualTransformation() else VisualTransformation.None
    val keyboardOptions =
        if (isNumeric) KeyboardOptions(keyboardType = KeyboardType.Number) else  KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        )
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
                maxLines = lines,
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
