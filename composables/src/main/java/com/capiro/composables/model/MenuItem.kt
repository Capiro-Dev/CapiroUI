package com.capiro.composables.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class MenuItemCapiro(
    @DrawableRes val image: Int,
    val title: String,
    val borderColor: Color,
    val colorBack: Color,
    val execute: () -> Unit
)