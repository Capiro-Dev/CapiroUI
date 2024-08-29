package com.capiro.composables.util_composables

import TypographyProvider
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.card.CardCapiro
import com.capiro.composables.athomic_composables.image.ImageCapiro
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GrayCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import com.capiro.composables.theme.RedCapiro

/**
 * A composable that displays an item scanner UI, including item number, label, date, and a main content section.
 *
 * @param itemNumber The number associated with the scanned item.
 * @param scannedLabel The label or description of the scanned item.
 * @param date The date associated with the scan.
 * @param mainComposable The main composable content to be displayed within the scanner.
 * @param onDeleteClick remove the item
 */
@Composable
fun ItemManualCapiro(
    itemNumber: String,
    scannedLabel: String,
    mainComposable: @Composable () -> Unit,
    onDeleteClick: (() -> Unit)? = null,
    color: Color,
    colorBackground: Color

    ) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CardCapiro(
            backgroundColor = colorBackground,
            innerComposable = {
            Row(modifier = Modifier.fillMaxWidth()) {
                ItemManualHeader(itemNumber)

                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Top) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        ItemManualLabel(
                            color = color,
                            label = scannedLabel
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .weight(1f)
                        ) {
                            mainComposable()
                        }
                        if (onDeleteClick != null) {
                            ItemManualMore {
                                onDeleteClick()
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
private fun ItemManualHeader(
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
@Composable
private fun ItemManualLabel(
    label: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .background(color, RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = TypographyProvider.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/**
 * Displays a clickable text that triggers the 'more' action in the item scanner.
 *
 * @param onClick Callback triggered when the text is clicked.
 */
@Composable
private fun ItemManualMore(
    onClick: () -> Unit
) {
    Row(
        Modifier
            .padding(end = 20.dp, top = 8.dp)
            .width(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .width(2.dp)
                .height(20.dp)
                .background(GrayCapiro)
        )
        Image(
            modifier = Modifier
                .size(28.dp)
                .clickable { onClick() },
            painter = painterResource(id = R.drawable.delete),
            contentDescription = null
        )

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun ItemManualPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BeigeCapiro)
            .padding(16.dp)
    ) {
        ItemManualCapiro(
            itemNumber = "23456",
            color = RedCapiro,
            colorBackground = GreenSecondCapiro,
            scannedLabel = "A22W33Y33-12345",
            mainComposable = {
                Column {
                    Text(
                        modifier = Modifier.basicMarquee(),
                        text = "MAteo es muy sexy",
                        color = GreenCapiro,
                        style = TypographyProvider.typography.bodyMedium,

                        )
                }
            },
            onDeleteClick = {},
        )
    }

}

