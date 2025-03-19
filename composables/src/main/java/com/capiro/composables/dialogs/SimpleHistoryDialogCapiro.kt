package com.capiro.composables.dialogs

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.card.CardCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldSolidBackground
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.ItemScannerCapiro
import com.capiro.composables.util_composables.WeekFilterCapiro
import getTypography

@Composable
fun <T>SimpleDialogHistoryCapiro(
    modifier: Modifier = Modifier,
    title: String,
    isTheDialogOpenState: Boolean,
    allData: Array<T>,
    composableItem: @Composable (T)->Unit,
    onCloseDialogEvent: () -> Unit,
) {
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                SimpleDialogLayout(
                    modifier = modifier,
                    title = title,
                    allData = allData,
                    composableItem = composableItem,
                    onCloseDialogEvent = onCloseDialogEvent
                )
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        )

    }
}

@Composable
private fun <T> SimpleDialogLayout(
    modifier: Modifier = Modifier,
    title: String,
    allData: Array<T>,
    composableItem: @Composable (T) -> Unit,
    onCloseDialogEvent: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(5.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // TITLE
            Text(
                text = title,
                style = getTypography().bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = GreenCapiro
            )

            // LIST OF DATA
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Hace que la lista ocupe todo el espacio disponible
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allData.size) { index ->
                    val item = allData[index]
                    composableItem(item)
                }
            }
        }

        // BUTTON ALIGNED BOTTOM END
        ButtonCapiro(
            text = "Cerrar",
            onClick = onCloseDialogEvent,
            modifier = Modifier
                .align(Alignment.BottomEnd) // Alineado abajo a la derecha
                .padding(8.dp)
        )
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
private fun ItemCutHistoryCapiroView(item : ItemCutHistoryCapiro) {
    var isExpanded = remember { mutableStateOf(false) }
    Column {
        ItemScannerCapiro(
            itemNumber = "1",
            scannedLabel = item.code,
            mainComposable = {
                Column {
                    Text(
                        text = item.customer,
                        style = getTypography().bodyMedium,
                        color = GreenCapiro
                    )
                    Text(
                        text = item.variety,
                        style = getTypography().bodySmall,
                        color = GreenCapiro,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            onExpandClick = { isExpanded.value = !isExpanded.value },
            color = GreenCapiro,
            colorBackground = Color.White
        )
        if (isExpanded.value) {
            Box(Modifier.padding(8.dp)) {
                CardCapiro(
                    backgroundColor = Color.White,
                    innerComposable = {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = item.variety,
                                style = getTypography().bodyMedium,
                                modifier = Modifier.padding(8.dp),
                                color = GreenCapiro
                            )
                        }
                    }
                )
            }
        }
    }

}


@Preview
@Composable
fun PreviewSimpleDialogHistory() {

    SimpleDialogHistoryCapiro(
        title = "Historial",
        isTheDialogOpenState = true,
        allData = arrayOf(
            ItemCutHistoryCapiro(
                code = "A203Y25W04FLC-01501",
                variety = "Baltica",
                varietyType = "Type",
                varietyClass = "Class",
                varietyDegree = "Degree",
                customer = "All season inc",
                applicant = "Applicant",
                stems = "Stems",
                bouquets = "Bouquets",
                longitude = "Longitude",
                cutter = "Cutter",
                date = "Date",
                block = "Block",
                bed = "Bed"
            ),
            ItemCutHistoryCapiro(
                code = "A203Y25W04FLC-02504",
                variety = "Zippo",
                varietyType = "Type",
                varietyClass = "Class",
                varietyDegree = "Degree",
                customer = "Cerro punta",
                applicant = "Applicant",
                stems = "Stems",
                bouquets = "Bouquets",
                longitude = "Longitude",
                cutter = "Cutter",
                date = "Date",
                block = "Block",
                bed = "Bed"
            ),
            ItemCutHistoryCapiro(
                code = "A203Y25W04FLC-03505",
                variety = "Bonita",
                varietyType = "Type",
                varietyClass = "Class",
                varietyDegree = "Degree",
                customer = "Capiro chile",
                applicant = "Applicant",
                stems = "Stems",
                bouquets = "Bouquets",
                longitude = "Longitude",
                cutter = "Cutter",
                date = "Date",
                block = "Block",
                bed = "Bed"
            ),
            ItemCutHistoryCapiro(
                code = "A203Y25W04FLC-00501",
                variety = "Baltica",
                varietyType = "Type",
                varietyClass = "Class",
                varietyDegree = "Degree",
                customer = "All season inc",
                applicant = "Applicant",
                stems = "Stems",
                bouquets = "Bouquets",
                longitude = "Longitude",
                cutter = "Cutter",
                date = "Date",
                block = "Block",
                bed = "Bed"
            ),
            ItemCutHistoryCapiro(
                code = "A203Y25W04FLC-00504",
                variety = "Zippo",
                varietyType = "Type",
                varietyClass = "Class",
                varietyDegree = "Degree",
                customer = "Cerro punta",
                applicant = "Applicant",
                stems = "Stems",
                bouquets = "Bouquets",
                longitude = "Longitude",
                cutter = "Cutter",
                date = "Date",
                block = "Block",
                bed = "Bed"
            ),
            ItemCutHistoryCapiro(
                code = "A203Y25W04FLC-00505",
                variety = "Bonita",
                varietyType = "Type",
                varietyClass = "Class",
                varietyDegree = "Degree",
                customer = "Capiro chile",
                applicant = "Applicant",
                stems = "Stems",
                bouquets = "Bouquets",
                longitude = "Longitude",
                cutter = "Cutter",
                date = "Date",
                block = "Block",
                bed = "Bed"
            )
        ),
        composableItem = {ItemCutHistoryCapiroView(it)},
        onCloseDialogEvent = {
            Log.d("FATAL EXCEPTION","CLOSE")
        }
    )
}



