package com.capiro.composables.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.athomic_composables.RadioButtonTextCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldSolidBackground
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.OrangeCapiro
import getTypography
/**
 * A composable function that displays a dialog with a list and text field.
 *
 * Example usage:
 * ```
 *   ListTextFieldDialogCapiro(
 *         modifier = Modifier.padding(vertical = 68.dp),
 *         titleIdRes = R.string.longitude,
 *         isDialogOpenState = isDialogOpenState.value,
 *         dataList = dataList,
 *         text = dialogText.value,
 *         onSelectionChanged = { dialogText.value = it },
 *         onClose = { isDialogOpenState.value = false },
 *         onAccept = {
 *             isDialogOpenState.value = false
 *             },
 *         units = "cm"
 *     )
 * ```
 *
 * @param modifier The modifier to be applied to the layout.
 * @param titleIdRes The resource ID for the dialog title.
 * @param isDialogOpenState The state to determine if the dialog is open.
 * @param dataList The list of data to be displayed.
 * @param text The text to be displayed in the text field.
 * @param onSelectionChanged The callback to be invoked when the selection changes.
 * @param onClose The callback to be invoked when the dialog is closed.
 * @param onAccept The callback to be invoked when the accept button is clicked.
 * @param units The units to be displayed next to the text field.
 */
@Composable
fun ListTextFieldDialogCapiro(
    modifier: Modifier = Modifier,
    titleIdRes: Int,
    isDialogOpenState: Boolean,
    dataList: List<String>,
    text: String,
    onSelectionChanged: (String) -> Unit,
    onClose: () -> Unit,
    onAccept: () -> Unit,
    units: String = ""
) {
    if (isDialogOpenState) {
        Dialog(
            onDismissRequest = { onClose() },
            content = {
                ListTextFieldDialogLayout(
                    modifier = modifier,
                    text = text,
                    titleIdRes = titleIdRes,
                    allData = dataList.toTypedArray(),
                    onSelectionChanged = onSelectionChanged,
                    onAccept = onAccept,
                    units = units
                )
            }
        )
    }
}

/**
 * A private composable function that defines the layout for the dialog.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param text The text to be displayed in the text field.
 * @param onSelectionChanged The callback to be invoked when the selection changes.
 * @param titleIdRes The resource ID for the dialog title.
 * @param allData The array of data to be displayed.
 * @param onAccept The callback to be invoked when the accept button is clicked.
 * @param units The units to be displayed next to the text field.
 */
@Composable
private fun ListTextFieldDialogLayout(
    modifier: Modifier,
    text: String,
    onSelectionChanged: (String) -> Unit,
    @StringRes titleIdRes: Int,
    allData: Array<String>,
    onAccept: () -> Unit,
    units: String
) {
    Column(modifier = modifier.background(color = Color.White, shape = RoundedCornerShape(5))) {

        // TITLE
        Text(
            text = stringResource(id = titleIdRes),
            style = getTypography().bodyMedium,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = GreenCapiro
        )

        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // LIST OF DATA
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(allData.size) { index ->
                        ItemsList(
                            selectedItem = allData[index],
                            elementChecked = text,
                            onElementChange = onSelectionChanged
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.weight(1f)){
                    TextFieldSolidBackground(
                        text = text,
                        onTextChanged = onSelectionChanged
                    )
                }


                Text(
                    text = units,
                    style = getTypography().bodyMedium,
                    color = OrangeCapiro,
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonCapiro(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.general_bt_accept),
                    onClick = { onAccept() }
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

/**
 * A private composable function that defines the items in the list.
 *
 * @param selectedItem The selected item in the list.
 * @param elementChecked The element that is currently checked.
 * @param onElementChange The callback to be invoked when the element changes.
 */
@Composable
private fun ItemsList(
    selectedItem: String,
    elementChecked: String,
    onElementChange: (String) -> Unit
) {
    RadioButtonTextCapiro(
        selection = elementChecked,
        identifier = selectedItem,
        onClick = { onElementChange(selectedItem) }
    )
}
