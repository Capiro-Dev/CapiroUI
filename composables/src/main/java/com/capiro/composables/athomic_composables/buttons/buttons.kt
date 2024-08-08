package com.capiro.composables.athomic_composables.buttons

import TypographyProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro

/**
 * A composable function that displays a customizable button.
 *
 * @param modifier Modifier to be applied to the button.
 * @param text The text to be displayed on the button.
 * @param onClick The click handler for the button.
 * @param isEnabled Indicates whether the button is enabled or not.
 * @param border The color of the button's border when enabled.
 * @param borderIsNotEnabled The color of the button's border when disabled.
 * @param fontColor The color of the text when the button is enabled.
 * @param fontColorIsNotEnabled The color of the text when the button is disabled.
 * @param background The background color of the button when enabled.
 * @param backgroundIsNotEnabled The background color of the button when disabled.
 */
@Composable
fun ButtonCapiro(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true,

    // Border colors
    border: Color = GreenCapiro,
    borderIsNotEnabled: Color = GrayDarkCapiro,


    // Background colors
    background: Color = GreenCapiro,
    backgroundIsNotEnabled: Color = GrayClearCapiro,

    // font style
    textStyle: TextStyle = TypographyProvider.typography.bodyMedium
) {
    // Color state management
    val backgroundState = if (isEnabled) background else backgroundIsNotEnabled
    val borderState = if (isEnabled) border else borderIsNotEnabled


    Box(
        modifier = modifier
            .shadow(8.dp, RoundedCornerShape(30))
            .background(color = backgroundState, shape = RoundedCornerShape(30))
            .border(1.dp, borderState, RoundedCornerShape(30))
            .clickable(enabled = isEnabled) { onClick() }
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ButtonCapiroPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        ButtonCapiro(text = "Button", onClick = { /* TODO */ })
    }
}
