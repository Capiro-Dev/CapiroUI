package com.capiro.composables.util_composables

import TypographyProvider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.theme.GrayCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro

/**
 * A composable function that creates two toggleable buttons with the provided texts.
 * When one button is selected, the other is unselected, allowing only one active selection at a time.
 *
 * @param text1 The text to display on the first button.
 * @param text2 The text to display on the second button.
 * @param onClick1 The action to perform when the first button is clicked.
 * @param onClick2 The action to perform when the second button is clicked.
 * @param width The width of each button. Defaults to 200.dp.
 */
@Composable
fun TwoOptionsButtonCapiro(
    text1: String,
    text2: String,
    onClick1: () -> Unit,
    onClick2: () -> Unit
) {
    // Retrieves the text style from a typography provider.
    val fontProvider = TypographyProvider.typography.bodyMedium
    // Manages the selected state between the two buttons.
    var isFirstSelected by remember { mutableStateOf(true) }
    // Background colors for the buttons based on their selection state.
    val background1 = if (isFirstSelected) GreenCapiro else GrayCapiro
    val background2 = if (isFirstSelected) GrayCapiro else GreenCapiro
    // Text styles for the selected and unselected buttons.
    val selectedTextStyle = fontProvider.merge(
        color = WhiteCapiro,
        fontWeight = FontWeight.Bold
    )
    val unselectedTextStyle = fontProvider.merge(
        color = GrayDarkCapiro,
        fontWeight = FontWeight.Normal
    )

    // Assigns text styles based on the selected state.
    val textStyle1 = if (isFirstSelected) selectedTextStyle else unselectedTextStyle
    val textStyle2 = if (isFirstSelected) unselectedTextStyle else selectedTextStyle

    // Controls the stacking order of the buttons to ensure proper visual display.
    val zIndex1 = if (isFirstSelected) 1f else 0f
    val zIndex2 = if (isFirstSelected) 0f else 1f

    // Displays the two buttons side by side with a slight overlap.
    Row(horizontalArrangement = Arrangement.spacedBy((-20).dp)) {
        Box (modifier = Modifier.weight(1f).zIndex(zIndex1),) {

            ButtonCapiro(
                modifier = Modifier
                    .fillMaxWidth(),
                border = background1,
                text = text1,
                onClick = {
                    isFirstSelected = true
                    onClick1()
                },
                background = background1,
                textStyle = textStyle1
            )
        }
        Box (modifier = Modifier.weight(1f).zIndex(zIndex2),) {
            ButtonCapiro(
                border = background2,
                modifier = Modifier
                    .fillMaxWidth(),
                text = text2,
                onClick = {
                    isFirstSelected = false
                    onClick2()
                },
                background = background2,
                textStyle = textStyle2
            )
        }
    }
}

/**
 * A preview function for displaying the TwoOptionsButtonCapiro composable in Android Studio's Preview tool.
 */
@Composable
@Preview
fun TwoOptionsButtonCapiroPreview() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TwoOptionsButtonCapiro(
            text1 = "Option 1",
            text2 = "Option 2",
            onClick1 = {},
            onClick2 = {}
        )
    }
}
