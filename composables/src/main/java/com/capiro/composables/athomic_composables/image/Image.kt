package com.capiro.composables.athomic_composables.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.R

/**
 * Displays an image from the specified drawable resource ID.
 *
 * @param imageSourceId The resource ID of the drawable image to be displayed. This should be a valid drawable resource
 * identifier (e.g., `R.drawable.icon`).
 *
 * @param modifier An optional [Modifier] to customize the layout and appearance of the image.
 * By default, it is set to [Modifier] which means no additional modifications are applied unless specified.
 *
 * This composable function displays an image using the provided drawable resource ID. The image is rendered with
 * [ContentScale.Fit], ensuring the image scales to fit its container while maintaining its aspect ratio.
 */
@Composable
fun ImageCapiro(@DrawableRes imageSourceId:Int, modifier: Modifier = Modifier) {
    Image(
        modifier=modifier,
        painter=painterResource(imageSourceId),
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}

/**
 * Preview of [ImageCapiro] to display a sample image in the preview window.
 */
@Preview
@Composable
private fun ImageCapiroPreview() {
    ImageCapiro(imageSourceId = R.drawable.icon)
}
