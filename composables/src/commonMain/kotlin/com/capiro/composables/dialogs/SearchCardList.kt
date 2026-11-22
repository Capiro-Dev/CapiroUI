package com.capiro.composables.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.athomic_composables.textfield.SearchBarCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import getTypography

/**
 * A searchable list dialog whose rows are free-height cards — for lists where each item carries
 * more than one line (e.g. a bed with its plantings). Same frame as `SearchListDialogCapiro` (white,
 * green bold title), with [SearchBarCapiro] on top; `SearchListDialogCapiro` keeps fixed 44dp text
 * rows.
 *
 * Stateless: the caller filters [items] by [searchText]; the dialog only renders them. Each row is
 * drawn by [itemContent], which receives the row's index in [items] and handles its own click (so a
 * card can use its own shape and ripple).
 *
 * @param emptyText shown instead of the list when [items] is empty (e.g. "Sin resultados").
 * @param maxHeight caps the dialog; the list scrolls inside it.
 */
@Composable
fun <T> SearchCardListDialogCapiro(
    title: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    items: List<T>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    searchPlaceholder: String = "Buscar",
    emptyText: String? = null,
    maxHeight: Dp = 560.dp,
    itemKey: ((index: Int, item: T) -> Any)? = null,
    itemContent: @Composable (index: Int, item: T) -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = maxHeight)
                .background(Color.White, RoundedCornerShape(5.dp))
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            Text(
                text = title,
                style = getTypography().bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = GreenCapiro,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            )
            SearchBarCapiro(
                searchText = searchText,
                onSearchTextChange = onSearchTextChange,
                placeholder = searchPlaceholder,
            )
            if (items.isEmpty() && emptyText != null) {
                Text(
                    text = emptyText,
                    style = getTypography().bodyMedium,
                    textAlign = TextAlign.Center,
                    color = GrayDarkCapiro,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    // Room for the cards' shadows at the edges and the bottom corner.
                    contentPadding = PaddingValues(top = 12.dp, bottom = 16.dp, start = 2.dp, end = 2.dp),
                ) {
                    itemsIndexed(items, key = itemKey) { index, item -> itemContent(index, item) }
                }
            }
        }
    }
}
