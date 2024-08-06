package com.capiro.composables.screens

import TypographyProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.image.ImageCapiro
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GreenCapiro



/**
 * A splash screen layout that displays an app icon, a support message, and the app version.
 *
 * @param iconAppResource The drawable resource ID of the app icon to be displayed on the splash screen.
 * @param version The version string to be displayed at the bottom of the splash screen.
 */
@Composable
fun SplashScreenCapiro(
    iconAppResource: Int,
    version: String
) {

    val typo= TypographyProvider.typography

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BeigeCapiro),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(2f))

        Column(
            modifier = Modifier
                .weight(5f)
                .padding(horizontal = 32.dp, vertical = 64.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ICON APP
            ImageCapiro(
                imageSourceId = iconAppResource,
                modifier = Modifier.height(240.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = version,
                    style = typo.bodySmall,
                    color = GreenCapiro,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}


@Preview
@Composable
private fun SplashScreenCapiroPreview() {
    SplashScreenCapiro(
        iconAppResource = R.drawable.esquejes_logo,
        version = "v1.0.0"
    )
}