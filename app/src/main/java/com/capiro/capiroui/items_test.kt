package com.capiro.capiroui

import TypographyProvider
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GreenCapiro

import com.capiro.composables.util_composables.ItemScannerCapiro

@Composable
@Preview
fun TestItems() {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        ItemScannerCapiro(
            itemNumber = "123456",
            scannedLabel = "A22Y32W33-44534",
            mainComposable = {
                Text(
                    text = "Amesthy Dark red",
                    style = TypographyProvider.typography.bodyMedium,
                    color = GreenCapiro
                )
            },
            onExpandClick = {}
        )
    }

}
