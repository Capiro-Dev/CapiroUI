package com.capiro.composables.athomic_composables.buttons

import TypographyProvider
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.RedCapiro

//********************************************
//               BUTTON IMAGE
//********************************************

/**
 * A composable function that displays a button with an image, optional label, and state.
 *
 * @param imageRes The drawable resource for the button image.
 * @param onClick The click handler for the button.
 * @param size The size of the button.
 * @param modifier Modifier to be applied to the button.
 * @param label Optional label text to be displayed below the image.
 * @param borderColor The border color of the button when enabled.
 * @param isEnabled Indicates whether the button is enabled.
 */
@Composable
fun ButtonImageCapiro(
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
    size: Dp,
    modifier: Modifier = Modifier,
    label: String? = null,
    borderColor: Color = GreenCapiro,
    isEnabled: Boolean = true
) {
    val actualBorderColor = if (isEnabled) borderColor else GrayDarkCapiro
    val textColor = if (isEnabled) GreenCapiro else GrayDarkCapiro

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(size), contentAlignment = Alignment.TopEnd) {
            IconButton(
                onClick = { if (isEnabled) onClick() },
                enabled = isEnabled,
                modifier = modifier
                    .size(size)
                    .background(color = Color.Transparent)
            ) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(width = 2.dp, shape = RoundedCornerShape(20), color = actualBorderColor)
                        .clip(RoundedCornerShape(20))
                        .scale(0.8f)
                )
            }

            if (!isEnabled) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    tint = RedCapiro,
                    modifier = Modifier.scale(0.8f)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = GrayDarkCapiro.copy(alpha = 0.5f))
                )
            }
        }

        if (label != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                color = textColor,
                style = TypographyProvider.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
private fun ButtonImageCapiroPreview() {
    Box(Modifier.padding(16.dp)) {
        ButtonImageCapiro(
            imageRes = R.drawable.icon,
            label = "Button",
            onClick = {  },
            size = 50.dp,
            isEnabled = true
        )
    }
}
