package com.capiro.composables.athomic_composables.spinners

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
 * Displays the selected item with a label.
 *
 * @param labelResourceId Resource ID for the label text.
 * @param selectedItem The currently selected item.
 * @param onClick Callback function to handle clicks.
 * @param isErrorActive Boolean indicating if there's an error (affects label color).
 */
@Composable
fun SpinnerButtonUnderlined(
    @StringRes labelResourceId: Int,
    selectedItem: String,
    onClick: () -> Unit,
    isErrorActive: Boolean = false
) {
    val labelColor = if (isErrorActive) RedCapiro else BlackCapiro

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


