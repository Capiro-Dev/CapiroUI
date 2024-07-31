package com.capiro.composables.util_composables

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.theme.GreenCapiro
import getTypography


/**
 * Displays a calendar bar with day, day of the week, and month information.
 *
 * @param day The day of the month to be displayed.
 * @param dayOfWeek The name of the day of the week (e.g., "Monday").
 * @param month The name of the month (e.g., "January").
 * @param spacerHeight The height of the space below the month text. Default is 6.dp.
 * @param onCalendarClick A lambda function to be invoked when the calendar icon is clicked.
 */
@Composable
fun CalendarBar(
    day: String,
    dayOfWeek: String,
    month: String,
    spacerHeight: Dp = 6.dp,
    onCalendarClick: () -> Unit
) {
    val type = getTypography()
    val heightDayRow = remember { mutableStateOf(0.dp) }
    val text = buildAnnotatedString {
        withStyle(style = type.bodyMedium.toSpanStyle()) {
            append(dayOfWeek)
            append("\n")
        }
        withStyle(style = type.bodyMedium.toSpanStyle().copy(fontWeight = FontWeight.Bold)) {
            append(month)
        }
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.Bottom) {
            Box(
                // Modifier for Box could be uncommented if needed
                // modifier = Modifier.height(heightDayRow.value * 2),
                // contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    modifier = Modifier.onSizeChanged { heightDayRow.value = it.height.dp },
                    text = day,
                    style = type.displayLarge,
                    color = GreenCapiro,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = text,
                    style = type.bodyMedium,
                    color = GreenCapiro,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(spacerHeight))
            }
        }

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp)
                .clickable { onCalendarClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview
@Composable
fun CalendarBarPreview() {
    CalendarBar(
        day = "12",
        dayOfWeek = "Monday",
        month = "January",
        onCalendarClick = {}
    )
}