package com.capiro.capiroui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.switchcapiro.SwitchCapiro

@Composable
@Preview
fun TestSwitch() {
    val isChecked = remember { mutableStateOf(false) }
    SwitchCapiro(text = "hello switch" , isChecked = isChecked.value, onCheckedChange = {isChecked.value= !isChecked.value} )
}