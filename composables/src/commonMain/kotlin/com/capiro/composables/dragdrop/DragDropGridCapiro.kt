package com.capiro.composables.dragdrop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.capiro.composables.theme.WhiteCapiro

/**
 * A reorderable grid with an iOS-style long-press "jiggle" edit mode: long-press any tile to
 * enter edit mode (drag to reorder), tap the badge on a non-dragged tile to archive it out of
 * the grid. This composable only reports index-based reorder/archive intents — the caller owns
 * the id list and the archived-items store (pair with [ArchiveTrayCapiro] to display/restore
 * them).
 *
 * @param T Item type rendered by [itemContent]. Resolved from [itemIds] via [getItem]; ids with
 * no match are skipped.
 * @param itemIds Stable ids, in display order. Drives both the grid and the drag/reorder math.
 * @param onReorder Called (possibly several times per drag) with the index swap to apply.
 * @param onArchiveItem Called with the index to remove when a tile's archive badge is tapped.
 * @param onDragEnd Called once a drag gesture ends — a good place to persist the new order.
 * @param archiveBadge Slot for the edit-mode badge shown on each non-dragged tile; defaults to
 * a red circular archive icon.
 * @param itemContent Renders one tile; `isDragged` is true only for the tile currently being
 * dragged (typically used to suppress the tile's own hover/press affordance while it's moving).
 */
@Composable
fun <T> DragDropGridCapiro(
    itemIds: List<String>,
    getItem: (String) -> T?,
    columns: Int,
    onReorder: (fromIndex: Int, toIndex: Int) -> Unit,
    onArchiveItem: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    onDragEnd: () -> Unit = {},
    archiveBadge: @Composable () -> Unit = { DefaultArchiveBadgeCapiro() },
    itemContent: @Composable (item: T, isDragged: Boolean) -> Unit,
) {
    val dragDropState = rememberDragDropGridStateCapiro(
        columns = columns,
        onReorder = onReorder,
        onArchiveItem = onArchiveItem,
        getItemIds = { itemIds },
    )

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                dragDropState.gridOffset = coordinates.positionInRoot()
            }
            .pointerInput(Unit) {
                detectTapGestures {
                    if (dragDropState.isEditMode) {
                        dragDropState.deactivateEditMode()
                    }
                }
            },
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            contentPadding = PaddingValues(bottom = 80.dp),
            userScrollEnabled = !dragDropState.isDragging,
        ) {
            items(itemIds.size, key = { itemIds[it] }) { index ->
                val itemId = itemIds[index]
                val item = getItem(itemId) ?: return@items

                val isDragged = dragDropState.isDragging && dragDropState.draggedItemId == itemId

                Box(
                    modifier = Modifier
                        .zIndex(if (isDragged) 10f else 0f)
                        .graphicsLayer {
                            if (isDragged) {
                                val translation = dragDropState.getDragTranslation()
                                translationX = translation.x
                                translationY = translation.y
                                scaleX = 1.1f
                                scaleY = 1.1f
                                shadowElevation = 16f
                                alpha = 0.9f
                            }
                        }
                        .onGloballyPositioned { coordinates ->
                            val pos = coordinates.positionInRoot()
                            dragDropState.updateItemPosition(itemId, pos)
                            if (dragDropState.itemSize == IntSize.Zero) {
                                dragDropState.itemSize = coordinates.size
                            }
                        }
                        .pointerInput(itemId) {
                            detectDragGesturesAfterLongPress(
                                onDragStart = { dragDropState.onDragStart(itemId) },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    dragDropState.onDrag(dragAmount)
                                },
                                onDragEnd = {
                                    dragDropState.onDragEnd()
                                    onDragEnd()
                                },
                                onDragCancel = { dragDropState.onDragCancel() },
                            )
                        },
                ) {
                    itemContent(item, isDragged)

                    if (dragDropState.isEditMode && !isDragged) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = (-4).dp, y = 4.dp)
                                .zIndex(20f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) {
                                    dragDropState.archiveItem(itemId)
                                },
                        ) {
                            archiveBadge()
                        }
                    }
                }
            }
        }
    }
}

/** Default [DragDropGridCapiro] archive badge: a small red circle with an archive icon. */
@Composable
fun DefaultArchiveBadgeCapiro() {
    Box(
        modifier = Modifier
            .size(26.dp)
            .background(Color.Red, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Archive,
            contentDescription = null,
            tint = WhiteCapiro,
            modifier = Modifier.size(16.dp),
        )
    }
}
