package com.capiro.composables.athomic_composables.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayClearCapiro
import getTypography

@Composable
fun TextFieldSolidBackground(
    text: String,
    onTextChanged: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text // Parámetro para definir el tipo de entrada
) {
    val typo = getTypography()

    TextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GrayClearCapiro,
            unfocusedContainerColor = GrayClearCapiro,
            disabledContainerColor = GrayClearCapiro,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth(),
        value = text,
        onValueChange = onTextChanged,
        textStyle = typo.bodyMedium,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType) // Configura el tipo de teclado
    )
}
