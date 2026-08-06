package com.capiro.composables.dialogs

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.radiobutton.RadioButtonTextCapiro
import com.capiro.composables.theme.GreenCapiro
import getTypography


@Composable
fun RadioButtonDialogCapiro(
    modifier: Modifier = Modifier,
    @StringRes titleIdRes: Int,
    isTheDialogOpenState: Boolean,
    allData: Array<String>,
    onCloseDialogEvent: () -> Unit,
    text: String,
    onSelectionChanged: (String) -> Unit,
){
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                ListDialogRadioButtonLayout(
                    modifier = modifier,
                    titleIdRes = titleIdRes,
                    allData = allData,
                    text = text,
                    onSelectionChanged = onSelectionChanged,
                    onCloseDialogEvent = { onCloseDialogEvent()}
                )

            })
    }
}

@Composable
fun ListDialogRadioButtonLayout(
    modifier: Modifier,
    @StringRes titleIdRes: Int,
    allData: Array<String>,
    text: String,
    onSelectionChanged: (String) -> Unit,
    onCloseDialogEvent: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.White, RoundedCornerShape(5.dp))
            .padding(16.dp) // Agregamos padding general
    ) {

        Text(
            text = stringResource(id = titleIdRes),
            style = getTypography().bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = GreenCapiro
        )


        // LIST OF DATA con weight(1f) para expandirse y empujar el botón
        LazyColumn(
            modifier = Modifier
                .weight(1f)
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

        // BOTÓN ACEPTAR al final
        ButtonCapiro(
            text = "Aceptar",
            onClick = { onCloseDialogEvent() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp) // Espacio entre la lista y el botón
        )
    }
}


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

@Preview
@Composable
fun ListDialogRadioButtonCapiroPreview() {
    val dialogText = remember { mutableStateOf("") }
    val isOpenDialog = remember { mutableStateOf(false) }
    Button(content = {Text("Open")}, onClick = {
        isOpenDialog.value=true
    })
    RadioButtonDialogCapiro(
        modifier = Modifier.height(400.dp),
        titleIdRes = R.string.list_dialog,
        isTheDialogOpenState = isOpenDialog.value,
        allData = arrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15"
        ),
        onCloseDialogEvent = {isOpenDialog.value = false},
        text = dialogText.value,
        onSelectionChanged = { dialogText.value = it },
    )

}
