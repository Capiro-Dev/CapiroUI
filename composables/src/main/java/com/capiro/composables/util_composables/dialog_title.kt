package com.capiro.capiroui.util_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GreenCapiro


@Composable
fun DialogTitle(text: String, iconHeader: ImageVector) {

    Column(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = iconHeader,
                    contentDescription = null,
                    tint = GreenCapiro
                )

                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    color = GreenCapiro,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Divider( modifier = Modifier.width(240.dp), thickness = 1.dp, color = Color.DarkGray)
        }

    }

}

@Preview
@Composable
private fun DialogTitlePreview() {
    DialogTitle(text = "Title", iconHeader = Icons.Filled.VerifiedUser)
}