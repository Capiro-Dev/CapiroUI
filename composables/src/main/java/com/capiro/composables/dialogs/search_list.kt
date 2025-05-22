package com.capiro.composables.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.athomic_composables.textfield.TextFieldSolidBackground
import com.capiro.composables.theme.GreenCapiro
import getTypography


/**
 * Displays a dialog containing a searchable list.
 *
 * @param modifier Modifier to be applied to the dialog.
 * @param searchText The current text in the search input.
 * @param onSearchTextChange Callback to handle changes to the search text.
 * @param titleIdRes Resource ID for the dialog title.
 * @param isTheDialogOpenState Boolean flag to indicate if the dialog is open.
 * @param allData Array of data items to display in the list.
 * @param onCleanText Callback to clear the search text.
 * @param onSearchItemSelectedChangeState Callback when an item from the list is selected.
 * @param onCloseDialogEvent Callback to close the dialog.
 */
@Composable
fun SearchListDialogCapiro(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    @StringRes titleIdRes: Int,
    overTitle: String? = null,
    isTheDialogOpenState: Boolean,
    allData: Array<String>,
    onCleanText: () -> Unit,
    onSearchItemSelectedChangeState: (String) -> Unit,
    onCloseDialogEvent: () -> Unit,
) {
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                SearchListDialogLayout(
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    modifier = modifier,
                    titleIdRes = titleIdRes,
                    overTitle = overTitle,
                    allData = allData,
                    searchItemSelectedState = onSearchItemSelectedChangeState,
                    onCleanText = onCleanText
                )
            })
    }
}

/**
 * Layout for the search list dialog.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param titleIdRes Resource ID for the title text.
 * @param allData Array of data items to display in the list.
 * @param searchItemSelectedState Callback when an item from the list is selected.
 * @param onCleanText Callback to clear the search text.
 * @param onSearchTextChange Callback to handle changes to the search text.
 * @param searchText The current text in the search input.
 */
@Composable
private fun SearchListDialogLayout(
    modifier: Modifier,
    overTitle: String?,
    @StringRes titleIdRes: Int,
    allData: Array<String>,
    searchItemSelectedState: (String) -> Unit,
    onCleanText: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    searchText: String
) {
    Column(modifier = modifier.background(color = Color.White, RoundedCornerShape(5.dp))) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if(overTitle != null) {
                Text(
                    text = overTitle,
                    style = getTypography().bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = GreenCapiro
                )
            }
            // TITLE
            Text(
                text = stringResource(id = titleIdRes),
                style = getTypography().bodyMedium,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = GreenCapiro
            )

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                // SEARCH TEXT
                Box(modifier = Modifier.weight(1f)) {
                    TextFieldSolidBackground(
                        text = searchText,
                        onTextChanged = onSearchTextChange
                    )
                }

                // CLEAN TEXT
                Image(
                    modifier = Modifier.clickable { onCleanText() }.size(32.dp),
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(GreenCapiro),
                )
            }

            // LIST OF DATA
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(allData.size) { index ->
                        ItemsList(allData[index], searchItemSelectedState)
                    }
                }
            }
        }
    }
}

/**
 * Item view for the list in the search dialog.
 *
 * @param variety The text to display for the item.
 * @param itemSelectedEvent Callback when the item is selected.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ItemsList(
    variety: String,
    itemSelectedEvent: (String) -> Unit,
) {
    val textColor = remember { GreenCapiro }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clickable { itemSelectedEvent(variety) },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.basicMarquee(),
            text = variety,
            color = textColor,
            style = getTypography().bodyMedium
        )
    }
}
