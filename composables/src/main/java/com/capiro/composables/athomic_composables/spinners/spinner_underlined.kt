package com.capiro.composables.athomic_composables.spinners

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.theme.BlackCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.RedCapiro
import getTypography


/**
 * Displays a spinner with an underlined selected item.
 *
 * @param items List of items to be displayed in the dropdown menu.
 * @param selectedItem The currently selected item.
 * @param labelResourceId Resource ID for the label text.
 * @param onItemSelectedChange Callback function to handle item selection changes.
 * @param isErrorActive Boolean indicating if there's an error (affects label color).
 */
@Composable
fun SpinnerUnderlined(
    items: List<String>,
    selectedItem: String,
    @StringRes labelResourceId: Int,
    onItemSelectedChange: (String) -> Unit,
    isErrorActive: Boolean = false
) {
    val isExpanded = remember { mutableStateOf(false) }
    val labelColor = if (isErrorActive) RedCapiro else BlackCapiro

    Column {
        SpinnerUnderLinedSelectedItem(
            labelResourceId = labelResourceId,
            selectedItem = selectedItem,
            onClick = { isExpanded.value = !isExpanded.value },
            labelColor = labelColor
        )

        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(
                                text = label,
                                style = getTypography().bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = GreenCapiro
                            )
                        }
                    },
                    onClick = {
                        isExpanded.value = false
                        onItemSelectedChange(label)
                    }
                )
            }
        }
    }
}

/**
 * Displays the selected item with a label.
 *
 * @param labelResourceId Resource ID for the label text.
 * @param selectedItem The currently selected item.
 * @param onClick Callback function to handle clicks.
 * @param labelColor Color of the label text.
 */
@Composable
private fun SpinnerUnderLinedSelectedItem(
    @StringRes labelResourceId: Int,
    selectedItem: String,
    onClick: () -> Unit,
    labelColor: Color
) {
    Column(
        modifier = Modifier
            .height(54.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        // Display the label
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = labelResourceId),
            style = getTypography().bodyMedium,
            color = labelColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Display the selected item
            Text(
                modifier = Modifier.weight(1f),
                text = selectedItem,
                style = getTypography().bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = GreenCapiro,
                fontWeight = FontWeight.Bold
            )

            // Display the arrow icon
            Image(
                painter = painterResource(id = R.drawable.down_arrow),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        // Divider line
        Divider(thickness = 1.dp, color = GrayDarkCapiro)
    }
}