package com.capiro.composables.athomic_composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.R

@Composable
fun ImageCapiro(@DrawableRes imageSourceId:Int, modifier: Modifier = Modifier) {
    Image(
        modifier=modifier,
        painter=painterResource(imageSourceId),
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}

@Preview
@Composable
private fun ImageCapiroPreview() {
    ImageCapiro(imageSourceId = R.drawable.icon)
}
