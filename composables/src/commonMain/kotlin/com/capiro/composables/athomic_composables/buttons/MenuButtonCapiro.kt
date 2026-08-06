package com.capiro.composables.athomic_composables.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.model.MenuButtonItemCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import getTypography

/**
 * A module/feature navigation tile: a rounded icon square with a title below it. Multiplatform
 * (commonMain) replacement for the androidMain-only `ButtonMenu`.
 *
 * @param size Side length of the icon square — the title sits below it, outside [size].
 * @param enabled When false, the icon and title are dimmed and a blocked-icon overlay is shown;
 * taps are ignored.
 * @param badge Optional overlay in the tile's top-end corner (e.g. an edit-mode archive button).
 * Lets composables that arrange these tiles — such as
 * [com.capiro.composables.dragdrop.DragDropGridCapiro] — decorate a tile without this composable
 * knowing about that concept.
 */
@Composable
fun MenuButtonCapiro(
    item: MenuButtonItemCapiro,
    size: Dp,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    badge: (@Composable BoxScope.() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(size)
                    .shadow(4.dp, shape = RoundedCornerShape(20))
                    .background(color = item.backgroundColor, shape = RoundedCornerShape(20))
                    .clickable(enabled = enabled) { item.onClick() }
                    .border(
                        width = 2.dp,
                        color = if (enabled) item.borderColor else item.borderColor.copy(alpha = 0.35f),
                        shape = RoundedCornerShape(20),
                    )
                    .clip(RoundedCornerShape(20)),
            ) {
                Image(
                    painter = item.icon,
                    contentDescription = item.title,
                    alpha = if (enabled) 1f else 0.35f,
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(0.8f),
                )

                if (!enabled) {
                    Icon(
                        imageVector = Icons.Filled.Block,
                        contentDescription = null,
                        tint = GrayDarkCapiro,
                        modifier = Modifier.size(size / 2),
                    )
                }
            }

            if (badge != null) {
                Box(modifier = Modifier.align(Alignment.TopEnd), content = badge)
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item.title,
            style = getTypography().bodyMedium,
            color = if (enabled) GreenCapiro else GreenCapiro.copy(alpha = 0.5f),
            fontStyle = FontStyle.Italic,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun MenuButtonCapiroPreview() {
    MenuButtonCapiro(
        item = MenuButtonItemCapiro(
            id = "preview",
            title = "Module",
            icon = ColorPainter(Color.Gray),
            onClick = {},
        ),
        size = 80.dp,
    )
}
