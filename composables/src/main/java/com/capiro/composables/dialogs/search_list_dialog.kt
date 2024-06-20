package com.capiro.capiroui.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.DialogTitle
import com.capiro.composables.R


@Composable
fun SearchListDialogCapiro(
    @StringRes titleIdRes: Int,
    headerIcon: ImageVector,
    searchText: String,
    onSearchTextChangeEvent: (String) -> Unit,
    onItemSelectClickEvent: (String) -> Unit,
    onClearSearchClickEvent: () -> Unit,
    isTheDialogOpenState: Boolean,
    allData: Array<String>,
    onCloseDialogEvent: () -> Unit,
) {
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                SearchPestLayoutDialog(
                    titleIdRes = titleIdRes,
                    headerIcon = headerIcon,
                    searchText = searchText,
                    onSearchTextChangeEvent = onSearchTextChangeEvent,
                    onItemSelectClickEvent = onItemSelectClickEvent,
                    onClearSearchClickEvent = onClearSearchClickEvent,
                    allData = allData,
                    closeDialog = onCloseDialogEvent
                )
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchPestLayoutDialog(
    @StringRes titleIdRes: Int,
    headerIcon: ImageVector,
    searchText: String,
    onSearchTextChangeEvent: (String) -> Unit,
    onItemSelectClickEvent: (String) -> Unit,
    onClearSearchClickEvent: () -> Unit,
    allData: Array<String>,
    closeDialog: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .background(color = Color.White, RoundedCornerShape(5))
            .padding(2.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // TITLE
        Column() {
            DialogTitle(text = stringResource(id = titleIdRes), iconHeader = headerIcon)

            SearchBar(
                searchText = searchText,
                onSearchTextChangeEvent = onSearchTextChangeEvent,
                onClearSearchClickEvent = onClearSearchClickEvent
            )
            val filteredList = allData.filter { it.contains(searchText, ignoreCase = true) }
            LazyColumn(modifier = Modifier.weight(1f).padding(top=16.dp)) {
                items(count = filteredList.size
                ) { index ->
                    ItemsList( filteredList[index],onItemSelectClickEvent)
                }
            }

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                ButtonCapiro(
                    text = stringResource(id = R.string.general_bt_closeDialog),
                    onClick = { closeDialog() })
            }
        }
    }
}

@Composable
private fun ItemsList( item: String, onItemSelectedClickEvent: (String)-> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onItemSelectedClickEvent(item) }
    ) {

        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            /*Icon(
                modifier = Modifier.size(18.dp).padding(start = 8.dp),
                imageVector = Icons.Filled.Circle,
                contentDescription = null,
                tint = GrayDark
            )*/

            Text(
                text = item,
                color = GreenCapiro,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
            )
        }

        Divider(color = GrayDarkCapiro, thickness = 1.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchBar(
    searchText: String,
    onSearchTextChangeEvent: (String) -> Unit,
    onClearSearchClickEvent: ()-> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = GrayClearCapiro,
                shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,

        ) {
        // SEARCH ICON
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = GrayDarkCapiro,
            modifier = Modifier
                .size(32.dp)
                .padding(start = 8.dp)
        )
        // SEARCH TEXT
        BasicTextField(
            onValueChange = { onSearchTextChangeEvent(it) },
            value = searchText,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),

            )
        // CLEAR SEARCH
        IconButton(onClick = { onClearSearchClickEvent() }) {
            Icon(
                imageVector = Icons.Outlined.Cancel,
                contentDescription = null,
                tint = GrayDarkCapiro,
                modifier = Modifier.size(32.dp)
            )

        }

    }
}

@Preview
@Composable
private fun SearchListDialogPreview() {
    SearchListDialogCapiro(
        titleIdRes = R.string.general_bt_closeDialog,
        headerIcon = Icons.Default.Sync,
        searchText = "Search",
        onSearchTextChangeEvent = {},
        onItemSelectClickEvent = {},
        onClearSearchClickEvent = {},
        isTheDialogOpenState = true,
        allData = arrayOf("Item 1", "Item 2", "Item 3"),
        onCloseDialogEvent = {}
    )
}