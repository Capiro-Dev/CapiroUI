package com.capiro.composables.athomic_composables.buttons

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro
import getTypography

//********************************************
//                 ICON BUTTON
//********************************************

/**
 * A composable function that displays a customizable icon button with optional label.
 *
 * @param modifier Modifier to be applied to the button.
 * @param image The icon to be displayed on the button.
 * @param onClick The click handler for the button.
 * @param isRounded Indicates whether the button corners are rounded.
 * @param label Optional label text to be displayed below the icon.
 * @param fontColor The color of the label text.
 * @param iconColor The color of the icon in the default state.
 * @param iconColorPressed The color of the icon when pressed.
 * @param backgroundColor The background color of the button in the default state.
 * @param backgroundColorPressed The background color of the button when pressed.
 * @param lineBorderColor The border color of the button in the default state.
 * @param lineBorderColorPressed The border color of the button when pressed.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ButtonIconCapiro(
    modifier: Modifier = Modifier,
    image: ImageVector,
    onClick: () -> Unit,
    isRounded: Boolean = true,
    label: String? = null,

    // Font color
    fontColor: Color = GreenCapiro,

    // Icon colors
    iconColor: Color = WhiteCapiro,
    iconColorPressed: Color = GreenCapiro,

    // Background colors
    backgroundColor: Color = GreenCapiro,
    backgroundColorPressed: Color = WhiteCapiro,

    // Line border colors
    lineBorderColor: Color = GreenCapiro,
    lineBorderColorPressed: Color = GreenCapiro,
) {
    // Color states
    var iconColorState by remember { mutableStateOf(iconColor) }
    var backgroundColorState by remember { mutableStateOf(backgroundColor) }
    var lineBorderColorState by remember { mutableStateOf(lineBorderColor) }

    // Corner radius
    val cornerRadius = if (isRounded) 50 else 20

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            modifier = modifier
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            iconColorState = iconColorPressed
                            backgroundColorState = backgroundColorPressed
                            lineBorderColorState = lineBorderColorPressed
                            false
                        }
                        else -> {
                            iconColorState = iconColor
                            backgroundColorState = backgroundColor
                            lineBorderColorState = lineBorderColor
                            false
                        }
                    }
                }
                .clip(RoundedCornerShape(cornerRadius))
                .background(backgroundColorState)
                .border(2.dp, lineBorderColorState, RoundedCornerShape(cornerRadius)),
            onClick = {
                onClick()
                // Reset colors after click
                iconColorState = iconColor
                backgroundColorState = backgroundColor
                lineBorderColorState = lineBorderColor
            },
        ) {
            // Icon
            Icon(
                modifier = Modifier.scale(1.4f),
                imageVector = image,
                contentDescription = null,
                tint = iconColorState
            )
        }

        // Label
        label?.let {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = it,
                style = getTypography().bodySmall,
                color = fontColor,
            )
        }
    }
}

@Preview
@Composable
private fun ButtonIconCapiroPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ButtonIconCapiro(
            image = Icons.Filled.AccountBox,
            onClick = { /* TODO */ },
            isRounded = false,
            label = "Button"
        )
    }
}
