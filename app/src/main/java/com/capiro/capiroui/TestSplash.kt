package com.capiro.capiroui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.R
import com.capiro.composables.screens.SplashScreenCapiro

@Preview(showBackground = true)
@Composable
fun TestSplashScreen(){
    SplashScreenCapiro(iconAppResource = R.drawable.plagas_transparent, version = "1.0.0" )
}