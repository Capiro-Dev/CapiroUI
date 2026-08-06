package com.capiro.composables.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

@Deprecated(
    "Android-only. Use com.capiro.composables.model.MenuButtonItemCapiro (multiplatform) " +
        "with com.capiro.composables.athomic_composables.buttons.MenuButtonCapiro instead.",
)
data class MenuItemCapiro(
    @DrawableRes val image: Int,
    val title: String,
    val borderColor: Color,
    val colorBack: Color,
    val execute: () -> Unit
)