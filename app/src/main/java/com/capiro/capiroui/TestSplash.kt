package com.capiro.capiroui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.capiroui.screens.SplashScreenCapiro
import com.capiro.composables.dialogs.CustomDatePicker

@Preview(showBackground = true)
@Composable
fun TestSplashScreen(){
    SplashScreenCapiro(iconAppResource = com.capiro.composables.R.drawable.plagas_transparent, version = "1.0.0" )
}