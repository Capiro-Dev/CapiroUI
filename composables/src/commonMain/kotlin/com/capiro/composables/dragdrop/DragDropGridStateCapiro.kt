package com.capiro.composables.dragdrop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeSource

/**
 * Drag state for [DragDropGridCapiro]'s long-press-to-reorder grid. Tracks items by their
 * stable id (not index) to avoid stale-closure bugs while the backing list is mutated mid-drag.
 *
 * Multiplatform port of QualityMobile's `DragDropGridState`: the only behavioral change is the
 * reorder cooldown, which uses [TimeSource.Monotonic] instead of `System.currentTimeMillis()`
 * (unavailable outside the JVM/Android target).
 */
@Stable
class DragDropGridStateCapiro(
    val columns: Int,
) {
    var onReorder: (fromIndex: Int, toIndex: Int) -> Unit = { _, _ -> }
    var onArchiveItem: (index: Int) -> Unit = {}
    var getItemIds: () -> List<String> = { emptyList() }

    var isDragging by mutableStateOf(false)
        private set

    var draggedItemId by mutableStateOf<String?>(null)
        private set

    var rawFingerDelta by mutableStateOf(Offset.Zero)
        private set

    var isEditMode by mutableStateOf(false)

    var itemSize: IntSize = IntSize.Zero
    var gridOffset: Offset = Offset.Zero

    private val itemPositions = mutableMapOf<String, Offset>()
    private var dragStartSlotPos: Offset = Offset.Zero
    private var currentTargetSlotPos: Offset = Offset.Zero
    private var lastReorderMark: TimeSource.Monotonic.ValueTimeMark? = null
    private val reorderCooldown = 300.milliseconds

    fun updateItemPosition(id: String, position: Offset) {
        if (isDragging && id == draggedItemId) return
        itemPositions[id] = position
    }

    fun getDragTranslation(): Offset {
        return rawFingerDelta + (dragStartSlotPos - currentTargetSlotPos)
    }

    fun onDragStart(itemId: String) {
        isEditMode = true
        isDragging = true
        draggedItemId = itemId
        rawFingerDelta = Offset.Zero
        dragStartSlotPos = itemPositions[itemId] ?: Offset.Zero
        currentTargetSlotPos = dragStartSlotPos
        lastReorderMark = TimeSource.Monotonic.markNow()
    }

    fun onDrag(change: Offset) {
        rawFingerDelta += change
        val id = draggedItemId ?: return

        if (itemSize.width <= 0 || itemSize.height <= 0) return

        val mark = lastReorderMark
        if (mark != null && mark.elapsedNow() < reorderCooldown) return

        val ids = getItemIds()
        val currentIndex = ids.indexOf(id)
        if (currentIndex < 0) return

        val visualCenterX = dragStartSlotPos.x + rawFingerDelta.x + itemSize.width / 2f
        val visualCenterY = dragStartSlotPos.y + rawFingerDelta.y + itemSize.height / 2f

        val neighborOffsets = listOf(-1, 1, -columns, columns)
        val candidates = neighborOffsets
            .map { currentIndex + it }
            .filter { it in 0 until ids.size }
            .distinct()

        var bestTarget = -1
        var bestDistSq = Float.MAX_VALUE

        for (candidateIndex in candidates) {
            val candidateId = ids[candidateIndex]
            val candidatePos = itemPositions[candidateId] ?: continue
            val candidateCenterX = candidatePos.x + itemSize.width / 2f
            val candidateCenterY = candidatePos.y + itemSize.height / 2f

            val dx = visualCenterX - candidateCenterX
            val dy = visualCenterY - candidateCenterY
            val distSq = dx * dx + dy * dy

            if (distSq < bestDistSq) {
                bestDistSq = distSq
                bestTarget = candidateIndex
            }
        }

        if (bestTarget < 0 || bestTarget == currentIndex) return

        val targetId = ids[bestTarget]
        val targetPos = itemPositions[targetId] ?: return
        val targetCenterX = targetPos.x + itemSize.width / 2f
        val targetCenterY = targetPos.y + itemSize.height / 2f

        val currentCol = currentIndex % columns
        val currentRow = currentIndex / columns
        val targetCol = bestTarget % columns
        val targetRow = bestTarget / columns

        val crossed = when {
            targetRow != currentRow && targetCol != currentCol -> {
                val crossedY = if (targetRow > currentRow) visualCenterY > targetCenterY
                else visualCenterY < targetCenterY
                val crossedX = if (targetCol > currentCol) visualCenterX > targetCenterX
                else visualCenterX < targetCenterX
                crossedX && crossedY
            }
            targetRow != currentRow -> {
                if (targetRow > currentRow) visualCenterY > targetCenterY
                else visualCenterY < targetCenterY
            }
            else -> {
                if (targetCol > currentCol) visualCenterX > targetCenterX
                else visualCenterX < targetCenterX
            }
        }

        if (!crossed) return

        currentTargetSlotPos = targetPos
        lastReorderMark = TimeSource.Monotonic.markNow()

        if (abs(bestTarget - currentIndex) > 1) {
            val step = if (bestTarget > currentIndex) 1 else -1
            var i = currentIndex
            while (i != bestTarget) {
                onReorder(i, i + step)
                i += step
            }
        } else {
            onReorder(currentIndex, bestTarget)
        }
    }

    fun onDragEnd() {
        resetDragState()
    }

    fun onDragCancel() {
        resetDragState()
    }

    fun archiveItem(itemId: String) {
        val ids = getItemIds()
        val idx = ids.indexOf(itemId)
        if (idx >= 0) onArchiveItem(idx)
    }

    fun deactivateEditMode() {
        isEditMode = false
    }

    private fun resetDragState() {
        isDragging = false
        draggedItemId = null
        rawFingerDelta = Offset.Zero
        dragStartSlotPos = Offset.Zero
        currentTargetSlotPos = Offset.Zero
        lastReorderMark = null
    }
}

@Composable
fun rememberDragDropGridStateCapiro(
    columns: Int,
    onReorder: (fromIndex: Int, toIndex: Int) -> Unit,
    onArchiveItem: (index: Int) -> Unit,
    getItemIds: () -> List<String>,
): DragDropGridStateCapiro {
    val currentOnReorder by rememberUpdatedState(onReorder)
    val currentOnArchiveItem by rememberUpdatedState(onArchiveItem)
    val currentGetItemIds by rememberUpdatedState(getItemIds)
    val state = remember { DragDropGridStateCapiro(columns) }
    state.onReorder = currentOnReorder
    state.onArchiveItem = currentOnArchiveItem
    state.getItemIds = currentGetItemIds
    return state
}
