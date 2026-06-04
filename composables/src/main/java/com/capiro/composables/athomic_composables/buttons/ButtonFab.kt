package com.capiro.composables.athomic_composables.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.theme.GreenCapiro

/**
 * A composable function that displays a floating action button (FAB) with optional expandable items.
 *
 * @param imageRes1 The drawable resource for the first FAB item.
 * @param imageRes2 The drawable resource for the second FAB item.
 * @param imageRes3 The drawable resource for the third FAB item.
 * @param onClick1 The click handler for the first FAB item.
 * @param onClick2 The click handler for the second FAB item.
 * @param onClick3 The click handler for the third FAB item.
 */
@Composable
fun ButtonFabCapiro(
    @DrawableRes imageRes1: Int? = null,
    @DrawableRes imageRes2: Int? = null,
    @DrawableRes imageRes3: Int? = null,
    onClick1: (() -> Unit)? = null,
    onClick2: (() -> Unit)? = null,
    onClick3: (() -> Unit)? = null,
    innerPadding: Dp,
    iconSize: Dp
) {
    val isExpanded = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FabButtonItems(
            isExpanded = isExpanded.value,
            imageRes1 = imageRes1,
            imageRes2 = imageRes2,
            imageRes3 = imageRes3,
            onClick1 = onClick1,
            onClick2 = onClick2,
            onClick3 = onClick3,
            innerPadding = innerPadding,
            iconSize = iconSize
        )

        Spacer(modifier = Modifier.size(16.dp))

        val imageFab = if (isExpanded.value) R.drawable.fab_x else R.drawable.fab
        FabButton(
            imageRes = imageFab,
            onClick = { isExpanded.value = !isExpanded.value },
            innerPadding = 4.dp,
            iconSize = 50.dp
        )
    }
}

/**
 * A composable function that displays a single FAB with an image and a click handler.
 *
 * @param imageRes The drawable resource for the FAB icon.
 * @param onClick The click handler for the FAB.
 */
@Composable
private fun FabButton(
    @DrawableRes imageRes: Int?,
    onClick: (() -> Unit)?,
    innerPadding: Dp,
    iconSize:Dp
) {
    if (imageRes != null) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .padding(innerPadding)
                .size(iconSize)
                .clickable { onClick?.invoke() }
        )
    }
}

/**
 * A composable function that displays expandable FAB items.
 *
 * @param isExpanded Indicates whether the FAB items are expanded.
 * @param imageRes1 The drawable resource for the first FAB item.
 * @param imageRes2 The drawable resource for the second FAB item.
 * @param imageRes3 The drawable resource for the third FAB item.
 * @param onClick1 The click handler for the first FAB item.
 * @param onClick2 The click handler for the second FAB item.
 * @param onClick3 The click handler for the third FAB item.
 */
@Composable
private fun FabButtonItems(
    isExpanded: Boolean,
    @DrawableRes imageRes1: Int? = null,
    @DrawableRes imageRes2: Int? = null,
    @DrawableRes imageRes3: Int? = null,
    onClick1: (() -> Unit)? = null,
    onClick2: (() -> Unit)? = null,
    onClick3: (() -> Unit)? = null,
    innerPadding: Dp,
    iconSize: Dp
) {
    val borderCorner = remember { 16.dp }

    Column(
        modifier = Modifier
            .background(
                color = GreenCapiro,
                shape = RoundedCornerShape(
                    topStart = borderCorner,
                    topEnd = borderCorner,
                    bottomEnd = borderCorner,
                    bottomStart = borderCorner
                )
            )
            .width(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isExpanded) {
            FabButton(imageRes = imageRes1, onClick = onClick1, innerPadding = innerPadding,iconSize=iconSize)
            FabButton(imageRes = imageRes2, onClick = onClick2, innerPadding = innerPadding,iconSize=iconSize)
            FabButton(imageRes = imageRes3, onClick = onClick3, innerPadding = innerPadding,iconSize=iconSize)
        }
    }
}


@Preview
@Composable
private fun ButtonFabPreview() {
    ButtonFabCapiro(
        imageRes1 = R.drawable.error,
        imageRes2 = R.drawable.sync,
        imageRes3 = R.drawable.farm_secondary,
        onClick1 = { },
        onClick2 = { },
        onClick3 = { },
        innerPadding = 12.dp,
        iconSize = 32.dp
    )
}