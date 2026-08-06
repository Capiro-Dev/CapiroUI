package com.capiro.capiroui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.R
import com.capiro.composables.screens.SplashScreenCapiro

@Preview(showBackground = true)
@Composable
fun TestSplashScreen(){
    SplashScreenCapiro(icon = painterResource(id = R.drawable.esquejes_logo), version = "1.0.0" )
}