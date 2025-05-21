package com.capiro.composables.util_composables

import TypographyProvider
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.card.CardCapiro
import com.capiro.composables.athomic_composables.image.ImageCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro

/**
 * A composable that displays an item scanner UI, including item number, label, date, and a main content section.
 *
 * @param itemNumber The number associated with the scanned item.
 * @param scannedLabel The label or description of the scanned item.
 * @param date The date associated with the scan.
 * @param mainComposable The main composable content to be displayed within the scanner.
 * @param onExpandClick Optional callback triggered when the expand button is clicked.
 */
@Composable
fun ItemScannerCapiro(
    itemNumber: String,
    scannedLabel: String,
    mainComposable: @Composable () -> Unit,
    onExpandClick: (() -> Unit)? = null,
    color: Color,
    colorBackground: Color
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CardCapiro(
            backgroundColor = colorBackground,
            innerComposable = {
            Row(modifier = Modifier.fillMaxWidth()) {
                ItemScannerHeader(itemNumber)

                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Top) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        ItemScannerLabel(color = color, label = scannedLabel)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .weight(1f)
                        ) {
                            mainComposable()
                        }
                        if (onExpandClick != null) {
                            ItemScannerMore {
                                onExpandClick()
                            }
                        }
                    }
                }
            }
        })
    }
}

/**
 * Displays the header for the item scanner, including the item number and an associated image.
 *
 * @param itemNumber The number associated with the scanned item.
 */
@Composable
fun ItemScannerHeader(
    itemNumber: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = itemNumber,
            style = TypographyProvider.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = GreenCapiro
        )
        ImageCapiro(
            modifier = Modifier.size(32.dp),
            imageSourceId = R.drawable.flower
        )
    }
}

/**
 * Displays the label for the scanned item within the scanner.
 *
 * @param label The label or description of the scanned item.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemScannerLabel(
    color: Color,
    label: String,
    basicMarquee: Boolean = false
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .background(color, RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = if(basicMarquee)Modifier.basicMarquee() else Modifier,
            text = label,
            style = TypographyProvider.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1
        )
    }
}

/**
 * Displays a clickable text that triggers the 'more' action in the item scanner.
 *
 * @param onMoreClick Callback triggered when the text is clicked.
 */
@Composable
fun ItemScannerMore(
    onMoreClick: () -> Unit
) {
    Text(
        modifier = Modifier.clickable { onMoreClick() },
        text = stringResource(id = R.string.general_bt_more),
        style = TypographyProvider.typography.bodySmall,
        color = GrayDarkCapiro
    )
}



@Preview
@Composable
fun ItemScanPreview() {
    ItemScannerCapiro(
        itemNumber = "123456",
        scannedLabel = "Flower",
        mainComposable = {
            Column {
                Text(
                    text = "Date: 2022-10-10",
                    style = TypographyProvider.typography.bodySmall,
                    color = GreenSecondCapiro
                )
                Text(
                    text = "Time: 10:00",
                    style = TypographyProvider.typography.bodySmall,
                    color = GreenSecondCapiro
                )
            }
        },
        onExpandClick = { /*TODO*/ },
        color = GreenCapiro,
        colorBackground = Color.White
    )
}
