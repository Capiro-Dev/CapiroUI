package com.capiro.composables.dialogs

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.athomic_composables.textfield.TextFieldSolidBackground
import com.capiro.composables.theme.GreenCapiro
import getTypography
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.toast.ToastCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import java.nio.file.WatchEvent

@Composable
fun ListDialogCapiro(
    modifier: Modifier = Modifier,
    @StringRes titleIdRes: Int,
    isTheDialogOpenState: Boolean,
    allData: Array<String>,
    onCloseDialogEvent: () -> Unit,
    itemSelectedEvent: (String) -> Unit
) {
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                ListDialogLayout(
                    modifier = modifier,
                    titleIdRes = titleIdRes,
                    allData = allData,
                    itemSelectedEvent = {
                        itemSelectedEvent(it)
                        onCloseDialogEvent()
                    },
                    onCloseDialogEvent = { onCloseDialogEvent()}
                )
            })
    }
}

@Composable
fun ListDialogLayout(
    modifier: Modifier,
    @StringRes titleIdRes: Int,
    allData: Array<String>,
    itemSelectedEvent: (String) -> Unit,
    onCloseDialogEvent: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.White, RoundedCornerShape(5.dp))
            .padding(16.dp) // Agregamos padding general
    ) {
        // TITLE
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
                Items(allData[index], itemSelectedEvent = { itemSelectedEvent(it) })
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Items(
    item: String,
    itemSelectedEvent: (String) -> Unit,
) {
    val textColor = remember { GreenCapiro }
    var backgroundColor = remember { mutableStateOf(Color.White) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(color = backgroundColor.value)
            .padding(horizontal = 24.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        backgroundColor.value = GreenSecondCapiro
                        tryAwaitRelease() // Espera a que el usuario suelte el click/tap
                        backgroundColor.value = Color.White // Vuelve al color original
                    },
                    onTap = { itemSelectedEvent(item) }
                )
            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.basicMarquee(),
            text = item,
            color = textColor,
            style = getTypography().bodyMedium
        )
    }
}

@Preview
@Composable
fun ListDialogCapiroPreview() {
    val isOpenDialog = remember { mutableStateOf(false) }
    Button(content = {Text("Open")}, onClick = {
        isOpenDialog.value=true
    })
    ListDialogCapiro(
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
        itemSelectedEvent = { Log.d("qweqe", it) }
    )

}
