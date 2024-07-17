package com.capiro.composables.athomic_composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextCapiro(text: String,fontWeight: FontWeight, fontSize: TextUnit, color: Color) {
    Text(text = text, fontWeight = fontWeight,fontSize = fontSize)
}