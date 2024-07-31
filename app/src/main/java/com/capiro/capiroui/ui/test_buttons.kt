package com.capiro.capiroui.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.athomic_composables.buttons.FabCapiro

@Preview
@Composable
fun TestButtons() {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.BottomEnd) {
        FabCapiro(
            com.capiro.composables.R.drawable.history,
            com.capiro.composables.R.drawable.sync,
            null,
            {},
            {},
            null
        )
    }

}