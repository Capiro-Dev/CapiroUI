package com.capiro.composables.athomic_composables.spinners

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capiro.composables.EMPTY
import com.capiro.composables.R
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import getTypography

/**
 * A composable function that displays a spinner button.
 *
 * @param itemSelected The currently selected item to display.
 * @param labelResourceId The resource ID for the label text.
 * @param onClick The callback to be invoked when the button is clicked.
 */
@Composable
fun SpinnerButtonCapiro(
    itemSelected: String,
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
) {
    val isItemSelectedEmpty = itemSelected.isBlank() || itemSelected == EMPTY
    val label = if (isItemSelectedEmpty) "" else stringResource(id = labelResourceId)
    val displayText = if (isItemSelectedEmpty) stringResource(id = labelResourceId) else itemSelected
    val textColor = GreenCapiro
    val topLabelVisibility = if (isItemSelectedEmpty) false else true

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight().padding(vertical = 4.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // Label
                if (topLabelVisibility) {
                    Text(
                        text = label,
                        style = getTypography().bodySmall,
                        color = GrayDarkCapiro
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Display Text
                        Text(
                            text = displayText,
                            style = getTypography().bodyMedium,
                            color = textColor
                        )

                    // Arrow Icon
                    Image(
                        painter = painterResource(id = R.drawable.down_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
