package com.capiro.capiroui.screens

import androidx.compose.foundation.Image
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
import com.capiro.composables.athomic_composables.ImageCapiro
import com.capiro.composables.theme.GreenCapiro


@Composable
fun SplashScreenCapiro(
    iconAppResource: Int,
    version: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GreenCapiro),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(3f))

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
                modifier = Modifier.height(60.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Soportado por:",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Capiro dev",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = version,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


