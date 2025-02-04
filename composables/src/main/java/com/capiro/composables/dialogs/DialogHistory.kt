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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.capiro.composables.athomic_composables.card.CardCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldSolidBackground
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.ItemScannerCapiro
import com.capiro.composables.util_composables.WeekFilterCapiro
import getTypography

@Composable
fun DialogHistoryCapiro(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    title: String,
    isTheDialogOpenState: Boolean,
    allData: Array<ItemHistoryCapiro>,
    onCleanText: () -> Unit,
    onSearchItemSelectedChangeState: (String) -> Unit,
    onCloseDialogEvent: () -> Unit,
) {
    if (isTheDialogOpenState) {
        Dialog(
            onDismissRequest = { onCloseDialogEvent() },
            content = {
                DialogLayout(
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    modifier = modifier,
                    title = title,
                    allData = allData,
                    searchItemSelectedState = onSearchItemSelectedChangeState,
                    onCleanText = onCleanText
                )
            }, properties = DialogProperties(usePlatformDefaultWidth = false)
        )

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
private fun DialogLayout(
    modifier: Modifier,
    title: String,
    allData: Array<ItemHistoryCapiro>,
    searchItemSelectedState: (String) -> Unit,
    onCleanText: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    searchText: String
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(color = Color.White, RoundedCornerShape(5.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // TITLE
            Text(
                text = title,
                style = getTypography().bodyMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = GreenCapiro
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // SEARCH TEXT
                Box(modifier = Modifier.weight(1f)) {
                    TextFieldSolidBackground(
                        text = searchText,
                        onTextChanged = onSearchTextChange
                    )
                }

                // CLEAN TEXT
                Image(
                    modifier = Modifier
                        .clickable { onCleanText() }
                        .size(32.dp),
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(GreenCapiro),
                )
            }
            Box(modifier = Modifier.padding(top = 8.dp)) {
                WeekFilterCapiro()
            }

            // LIST OF DATA
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(allData.size) { index ->
                        val item = allData[index]
                        //ItemsList(allData[index], searchItemSelectedState)
                        ItemHistoryCapiroView(
                            code = item.code,
                            variety = item.variety,
                            customer = item.customer
                        )
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
private fun ItemHistoryCapiroView(
    code: String,
    variety: String,
    customer: String,
) {
    var isExpanded = remember { mutableStateOf(true) }
    Column {
        ItemScannerCapiro(
            itemNumber = "1",
            scannedLabel = code,
            mainComposable = {
                Column {
                    Text(
                        text = customer,
                        style = getTypography().bodyMedium,
                        color = GreenCapiro
                    )
                    Text(
                        text = variety,
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
                                text = variety,
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

data class ItemHistoryCapiro(
    val code: String,
    val block: String,
    val bed: String,
    val variety: String,
    val varietyType: String,
    val varietyClass: String,
    val varietyDegree: String,
    val customer: String,
    val applicant: String,
    val stems: String,
    val bouquets: String,
    val longitude: String,
    val cutter: String,
    val date: String,

    )

@Preview
@Composable
fun PreviewDialogHistory() {
    DialogHistoryCapiro(
        searchText = "Search",
        onSearchTextChange = {},
        title = "Historial",
        isTheDialogOpenState = true,
        allData = arrayOf(
            ItemHistoryCapiro(
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
            ItemHistoryCapiro(
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
            ItemHistoryCapiro(
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
        onCleanText = {},
        onSearchItemSelectedChangeState = {},
        onCloseDialogEvent = {}
    )
}
