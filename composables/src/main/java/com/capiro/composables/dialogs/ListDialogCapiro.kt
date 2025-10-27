package com.capiro.composables.dialogs

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.theme.GreenCapiro
import getTypography
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.theme.GreenSecondCapiro

@Deprecated(
    message = "Use SimpleListDialogsCapiro<T> instead",
    replaceWith = ReplaceWith("SimpleListDialogsCapiro(modifier, titleIdRes, isTheDialogOpenState, allData, onCloseDialogEvent, itemSelectedEvent)")
)
@Composable
fun ListDialogCapiro(
    modifier: Modifier = Modifier,
    @StringRes titleIdRes: Int,
    isTheDialogOpenState: Boolean,
    allData: Array<String>,
    onCloseDialogEvent: () -> Unit,
    itemSelectedEvent: (String) -> Unit
) {
    // Forward to the new generic implementation (keeps backward compatibility)
    SimpleListDialogsCapiro(
        modifier = modifier,
        titleIdRes = titleIdRes,
        isTheDialogOpenState = isTheDialogOpenState,
        allData = allData,
        onCloseDialogEvent = onCloseDialogEvent,
        itemSelectedEvent = itemSelectedEvent
    )
}

@Composable
fun <T> SimpleListDialogsCapiro(
    modifier: Modifier = Modifier,
    @StringRes titleIdRes: Int,
    isTheDialogOpenState: Boolean,
    allData: Array<T>,
    onCloseDialogEvent: () -> Unit,
    itemSelectedEvent: (T) -> Unit
) {
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                SimpleListDialogLayout(
                    modifier = modifier,
                    titleIdRes = titleIdRes,
                    allData = allData,
                    itemSelectedEvent = {
                        itemSelectedEvent(it)
                        onCloseDialogEvent()
                    },
                    onCloseDialogEvent = { onCloseDialogEvent() }
                )
            })
    }
}

@Composable
fun <T> SimpleListDialogLayout(
    modifier: Modifier,
    @StringRes titleIdRes: Int,
    allData: Array<T>,
    itemSelectedEvent: (T) -> Unit,
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
                SimpleItem(allData[index], itemSelectedEvent = { itemSelectedEvent(it) })
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
private fun <T> SimpleItem(
    item: T,
    itemSelectedEvent: (T) -> Unit,
) {
    val textColor = remember { GreenCapiro }
    val backgroundColor = remember { mutableStateOf(Color.White) }
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
            text = item.toString(),
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
    SimpleListDialogsCapiro(
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
