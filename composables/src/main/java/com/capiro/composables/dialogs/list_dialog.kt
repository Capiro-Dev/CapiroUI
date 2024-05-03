package com.capiro.capiroui.dialogs

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.capiroui.theme.GrayDarkCapiro
import com.capiro.capiroui.theme.GreenCapiro
import com.capiro.capiroui.util_composables.DialogTitle
import com.capiro.composables.R


@Composable
fun ListDialogCapiro(
    modifier: Modifier = Modifier,
    @StringRes titleIdRes: Int,
    headerIcon: ImageVector,
    rowIcon: ImageVector,
    isTheDialogOpenState: Boolean,
    allData: Array<String>,
    onSearchItemSelectedChangeState: (String) -> Unit,
    onCloseDialogEvent: () -> Unit,
): Boolean {
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                ListDialogLayout(
                    modifier = modifier,
                    titleIdRes = titleIdRes,
                    headerIcon = headerIcon,
                    rowIcon = rowIcon,
                    allData = allData,
                    searchItemSelectedState = onSearchItemSelectedChangeState,
                    closeDialog = onCloseDialogEvent,
                )
            }
        )
    }
    return true
}

@Composable
private fun ListDialogLayout(
    modifier: Modifier,
    @StringRes titleIdRes: Int,
    headerIcon: ImageVector,
    rowIcon: ImageVector,
    allData: Array<String>,
    searchItemSelectedState: (String) -> Unit,
    closeDialog: () -> Unit,

    ) {

    Column(
        modifier = Modifier
            .padding(40.dp)
            .background(color = White, RoundedCornerShape(5))
            .padding(24.dp)
        ,
                verticalArrangement = Arrangement . SpaceBetween
    ) {

        // TITLE

        DialogTitle(text = stringResource(id = titleIdRes), iconHeader = headerIcon)

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


        // ACCEPT
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            ButtonCapiro(
                text = stringResource(id = R.string.general_bt_closeDialog),
                onClick = { closeDialog() })
        }

    }

}

@Composable
private fun ItemsList(
    rowIcon: ImageVector,
    variety: String,
    itemSelectedEvent: (String) -> Unit,
) {
    val textColor = remember { GrayDarkCapiro }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clickable { itemSelectedEvent(variety) }, verticalArrangement = Arrangement.Bottom
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
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Divider(color = GreenCapiro, thickness = 1.dp)
    }
}


@Preview
@Composable
private fun ListDialogPreview() {
    ListDialogCapiro(
        titleIdRes = R.string.general_bt_closeDialog,
        headerIcon = Icons.Filled.Circle,
        rowIcon = Icons.Filled.Circle,
        isTheDialogOpenState = true,
        allData = arrayOf("data1", "data2", "data3"),
        onSearchItemSelectedChangeState = {},
        onCloseDialogEvent = {}
    )
}