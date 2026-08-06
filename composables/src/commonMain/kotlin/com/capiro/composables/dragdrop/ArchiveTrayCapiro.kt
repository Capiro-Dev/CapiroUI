package com.capiro.composables.dragdrop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.DialogTitle
import getTypography

/**
 * Dialog listing archived items with a way to restore each one. Pairs with
 * [DragDropGridCapiro]'s archive badge: this composable only renders whatever list the caller
 * passes in — hook [onRestoreItem] up to moving the id back into the grid's id list.
 *
 * @param T Item type rendered by [itemContent].
 * @param archivedItems Items currently archived, in display order.
 * @param getId Extracts the stable id passed to [onRestoreItem].
 * @param title Header text — caller-supplied so this stays localization-agnostic.
 * @param emptyMessage Shown instead of the grid when [archivedItems] is empty.
 */
@Composable
fun <T> ArchiveTrayCapiro(
    isOpen: Boolean,
    archivedItems: List<T>,
    getId: (T) -> String,
    title: String,
    emptyMessage: String,
    onDismiss: () -> Unit,
    onRestoreItem: (String) -> Unit,
    modifier: Modifier = Modifier,
    columns: Int = 3,
    itemContent: @Composable (item: T) -> Unit,
) {
    if (!isOpen) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(BeigeCapiro, RoundedCornerShape(20.dp))
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DialogTitle(text = title, onCloseClick = onDismiss)

            if (archivedItems.isEmpty()) {
                Text(
                    modifier = Modifier.padding(vertical = 32.dp),
                    text = emptyMessage,
                    style = getTypography().bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = GreenCapiro,
                )
            } else {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp),
                    columns = GridCells.Fixed(columns),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    items(archivedItems, key = getId) { item ->
                        RestorableArchivedItemCapiro(
                            item = item,
                            onRestore = { onRestoreItem(getId(item)) },
                            itemContent = itemContent,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> RestorableArchivedItemCapiro(
    item: T,
    onRestore: () -> Unit,
    itemContent: @Composable (item: T) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        itemContent(item)
        IconButton(onClick = onRestore) {
            Icon(
                imageVector = Icons.Filled.Restore,
                contentDescription = null,
                tint = GreenCapiro,
            )
        }
    }
}
