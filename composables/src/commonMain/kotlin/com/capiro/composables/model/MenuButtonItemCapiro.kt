package com.capiro.composables.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GreenCapiro

/**
 * Content + click handler for a [com.capiro.composables.athomic_composables.buttons.MenuButtonCapiro]
 * tile. Multiplatform counterpart of the androidMain-only `MenuItemCapiro` — uses [Painter]
 * instead of a `@DrawableRes` id so callers on iOS can supply one too (e.g.
 * `painterResource(Res.drawable.icon)`).
 *
 * @param id Stable identity, used as the grid key by [com.capiro.composables.dragdrop.DragDropGridCapiro].
 */
@Immutable
data class MenuButtonItemCapiro(
    val id: String,
    val title: String,
    val icon: Painter,
    val backgroundColor: Color = BeigeCapiro,
    val borderColor: Color = GreenCapiro,
    val onClick: () -> Unit,
)
