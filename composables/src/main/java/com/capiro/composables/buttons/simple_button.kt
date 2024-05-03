package com.capiro.composables.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleButton() {
        Button(modifier = Modifier.size(100.dp), onClick = { /* Do something! */ }){
            Text("Click me")
        }
}