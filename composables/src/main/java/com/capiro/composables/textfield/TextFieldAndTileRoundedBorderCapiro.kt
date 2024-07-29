package com.capiro.composables.textfield

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
import getTypography
import androidx.compose.material3.Typography
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TextFieldAndTileRoundedBorderCapiro(
    textInput: String,
    onTextChangeEvent: (String) -> Unit,
    label: String,
    title: String,
    errorMessage: String?=null,
    typography: Typography = getTypography(),
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    lines: Int = 1

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
            label = label,
            typography = typography,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isPassword = isPassword,
            errorMessage = errorMessage,
            imeAction = imeAction,
            lines = lines
        )
    }

}
