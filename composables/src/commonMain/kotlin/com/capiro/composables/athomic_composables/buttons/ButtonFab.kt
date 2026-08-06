package com.capiro.composables.athomic_composables.buttons

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.resources.Res
import com.capiro.composables.resources.fab
import com.capiro.composables.resources.fab_x
import com.capiro.composables.theme.GreenCapiro
import org.jetbrains.compose.resources.painterResource

/**
 * A composable function that displays a floating action button (FAB) with optional expandable items.
 *
 * The expanded/collapsed state can be used in two modes:
 *  - **Uncontrolled** (default): leave [expanded] `null` and the FAB manages its own state internally,
 *    exactly as before — fully backward compatible with existing call sites.
 *  - **Controlled**: pass a non-null [expanded] together with [onExpandedChange] to hoist the state.
 *    This lets the caller collapse the FAB from the outside (e.g. when the user taps elsewhere on the
 *    screen) by setting its own state to `false`.
 *
 * @param icon1 The [Painter] for the first FAB item.
 * @param icon2 The [Painter] for the second FAB item.
 * @param icon3 The [Painter] for the third FAB item.
 * @param onClick1 The click handler for the first FAB item.
 * @param onClick2 The click handler for the second FAB item.
 * @param onClick3 The click handler for the third FAB item.
 * @param expanded Optional controlled expanded state. When `null` the FAB is uncontrolled and keeps
 *   its own internal state.
 * @param onExpandedChange Invoked with the requested new expanded value when the main FAB is tapped.
 *   Required (together with [expanded]) to run in controlled mode.
 */
@Composable
fun ButtonFabCapiro(
    icon1: Painter? = null,
    icon2: Painter? = null,
    icon3: Painter? = null,
    onClick1: (() -> Unit)? = null,
    onClick2: (() -> Unit)? = null,
    onClick3: (() -> Unit)? = null,
    innerPadding: Dp,
    iconSize: Dp,
    expanded: Boolean? = null,
    onExpandedChange: ((Boolean) -> Unit)? = null
) {
    // Uncontrolled fallback: only used when the caller does not hoist [expanded].
    val internalExpanded = remember { mutableStateOf(false) }
    val isExpanded = expanded ?: internalExpanded.value

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FabButtonItems(
            isExpanded = isExpanded,
            icon1 = icon1,
            icon2 = icon2,
            icon3 = icon3,
            onClick1 = onClick1,
            onClick2 = onClick2,
            onClick3 = onClick3,
            innerPadding = innerPadding,
            iconSize = iconSize
        )

        Spacer(modifier = Modifier.size(16.dp))

        val fabIcon = painterResource(if (isExpanded) Res.drawable.fab_x else Res.drawable.fab)
        FabButton(
            icon = fabIcon,
            onClick = {
                val newValue = !isExpanded
                if (expanded == null) internalExpanded.value = newValue
                onExpandedChange?.invoke(newValue)
            },
            innerPadding = 4.dp,
            iconSize = 50.dp
        )
    }
}

/**
 * A composable function that displays a single FAB with an image and a click handler.
 *
 * @param icon The [Painter] for the FAB icon.
 * @param onClick The click handler for the FAB.
 */
@Composable
private fun FabButton(
    icon: Painter?,
    onClick: (() -> Unit)?,
    innerPadding: Dp,
    iconSize:Dp
) {
    if (icon != null) {
        Image(
            painter = icon,
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
 * @param icon1 The [Painter] for the first FAB item.
 * @param icon2 The [Painter] for the second FAB item.
 * @param icon3 The [Painter] for the third FAB item.
 * @param onClick1 The click handler for the first FAB item.
 * @param onClick2 The click handler for the second FAB item.
 * @param onClick3 The click handler for the third FAB item.
 */
@Composable
private fun FabButtonItems(
    isExpanded: Boolean,
    icon1: Painter? = null,
    icon2: Painter? = null,
    icon3: Painter? = null,
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
            FabButton(icon = icon1, onClick = onClick1, innerPadding = innerPadding, iconSize = iconSize)
            FabButton(icon = icon2, onClick = onClick2, innerPadding = innerPadding, iconSize = iconSize)
            FabButton(icon = icon3, onClick = onClick3, innerPadding = innerPadding, iconSize = iconSize)
        }
    }
}


@Preview
@Composable
private fun ButtonFabPreview() {
    ButtonFabCapiro(
        icon1 = ColorPainter(Color.Red),
        icon2 = ColorPainter(Color.Blue),
        icon3 = ColorPainter(Color.Green),
        onClick1 = { },
        onClick2 = { },
        onClick3 = { },
        innerPadding = 12.dp,
        iconSize = 32.dp
    )
}
