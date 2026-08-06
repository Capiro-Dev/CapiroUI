package com.capiro.composables.athomic_composables.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview

/**
 * Displays an image from the specified [Painter].
 *
 * @param painter The [Painter] to render (e.g. `painterResource(Res.drawable.icon)`).
 *
 * @param modifier An optional [Modifier] to customize the layout and appearance of the image.
 * By default, it is set to [Modifier] which means no additional modifications are applied unless specified.
 *
 * This composable function displays an image using the provided [Painter]. The image is rendered with
 * [ContentScale.Fit], ensuring the image scales to fit its container while maintaining its aspect ratio.
 */
@Composable
fun ImageCapiro(painter: Painter, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painter,
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
    ImageCapiro(painter = ColorPainter(Color.Gray))
}
