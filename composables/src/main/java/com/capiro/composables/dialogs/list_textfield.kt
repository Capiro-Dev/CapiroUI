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
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.DialogTitle
import getTypography

@Composable
fun ListTextFieldDialogCapiro(
    modifier: Modifier= Modifier,
    titleIdRes: Int,
    headerIcon: ImageVector,
    rowIcon: ImageVector,
    isDialogOpenState: Boolean,
    dataList: List<String>,
    text: String,
    onTextChanged: (String) -> Unit,
    onItemSelected: (String) -> Unit,
    onClose: () -> Unit,
    onAccept: () -> Unit


) {
    if (isDialogOpenState) {
        Dialog(onDismissRequest = { onClose()},
            content = {
                ListTextFieldDialogLayout(
                    modifier = modifier,
                    text = text,
                    onTextChanged = onTextChanged,
                    titleIdRes =titleIdRes,
                    headerIcon = headerIcon,
                    rowIcon = rowIcon,
                    allData = dataList.toTypedArray(),
                    searchItemSelectedState = onItemSelected,
                    onAccept = onAccept
                )
            })
    }
}

@Composable
private fun ListTextFieldDialogLayout(
    modifier: Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    @StringRes titleIdRes: Int,
    headerIcon: ImageVector,
    rowIcon: ImageVector,
    allData: Array<String>,
    searchItemSelectedState: (String) -> Unit,
    onAccept: () -> Unit,

    ) {


    Column(modifier= modifier.background(color = Color.White, RoundedCornerShape(5))) {

        // TITLE
        DialogTitle(text = stringResource(id = titleIdRes), iconHeader = headerIcon)

        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

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
                value = text,
                onValueChange = onTextChanged,
                textStyle = getTypography().bodyMedium,
            )


            Spacer(modifier = Modifier.size(16.dp))


            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                ButtonCapiro(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.general_bt_accept),
                    onClick = { onAccept() })
            }
            Spacer(modifier = Modifier.size(16.dp))

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