package com.capiro.composables.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.textfield.TextFieldCapiro
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.DialogTitle
import getTypography

@Composable
fun SearchListDialogCapiro(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    @StringRes titleIdRes: Int,
    headerIcon: ImageVector,
    rowIcon: ImageVector,
    isTheDialogOpenState: Boolean,
    allData: Array<String>,
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
                    headerIcon = headerIcon,
                    rowIcon = rowIcon,
                    allData = allData,
                    searchItemSelectedState = onSearchItemSelectedChangeState,
                    closeDialog = onCloseDialogEvent
                )
            })
    }
}

@Composable
private fun SearchListDialogLayout(
    modifier: Modifier,
    @StringRes titleIdRes: Int,
    headerIcon: ImageVector,
    rowIcon: ImageVector,
    allData: Array<String>,
    searchItemSelectedState: (String) -> Unit,
    closeDialog: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    searchText: String
) {


    Column(modifier = modifier.background(color = Color.White, RoundedCornerShape(5))) {

        // TITLE
        DialogTitle(
            text = stringResource(id = titleIdRes),
            iconHeader = headerIcon,
            onCloseClick = closeDialog
        )

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GrayClearCapiro,
                    unfocusedContainerColor = GrayClearCapiro,
                    disabledContainerColor = GrayClearCapiro,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.clip( RoundedCornerShape(40)),
                value = searchText,
                onValueChange = onSearchTextChange,
                textStyle = getTypography().bodyMedium,
                )


            // LIST OF DATA
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(allData.size) { index ->
                        ItemsList(rowIcon, allData[index], searchItemSelectedState)
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemsList(
    rowIcon: ImageVector,
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
        Row {
            Icon(
                imageVector = rowIcon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = variety,
                color = textColor,
                style = getTypography().bodyMedium
            )
        }
//        Divider(color = GreenCapiro, thickness = 1.dp)
    }
}