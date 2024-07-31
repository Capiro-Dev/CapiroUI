package com.capiro.composables.util_composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.theme.GreenCapiro
import getTypography



/**
 * A composable function to display a dialog title with an optional close button.
 *
 * @param text The text to display in the dialog title.
 * @param onCloseClick A lambda function invoked when the close button is clicked. If `null`, the close button will not be shown.
 *
 * This composable function renders a dialog title with a text and an optional close icon button.
 */
@Composable
fun DialogTitle(
    text: String,
    onCloseClick: (() -> Unit)? = null
) {
    val typo = getTypography()

    Column(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = GreenCapiro,
                style = typo.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Close button
            if (onCloseClick != null) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onCloseClick() },
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = GreenCapiro
                )
            }
        }

    }
}

/**
 * A composable function to display a centered image with rounded corners.
 *
 * @param imageResource The drawable resource ID for the image to display.
 *
 * This composable function renders an image with rounded corners and a fixed size.
 */
@Composable
fun DialogTitle2(@DrawableRes imageResource: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(20))
        )
    }
}

@Preview
@Composable
private fun DialogTitlePreview() {
    DialogTitle(
        text = "Dialog Title",
        onCloseClick = { /* Handle close click */ }
    )
}

@Preview
@Composable
private fun DialogTitle2Preview() {
    DialogTitle2(imageResource = R.drawable.flower)
}

