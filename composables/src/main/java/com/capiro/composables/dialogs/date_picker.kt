package com.capiro.composables.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.card.CardCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import com.capiro.composables.theme.WhiteCapiro
import getTypography
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

/**
 * Displays a custom date picker dialog. The dialog is shown when [isOpenDialog] is true.
 *
 * @param setLimit Optional parameter to limit selectable dates. Values can be "lower" for dates before today,
 * "upper" for dates after today, or null for no limit.
 * @param isOpenDialog Boolean flag to control the visibility of the dialog.
 * @param onDateSelected Callback triggered when a date is selected.
 * @param onCloseDialog Callback triggered when the dialog is closed.
 */
@Composable
fun CustomDatePicker(
    setLimit: String? = null,
    isOpenDialog: Boolean = false,
    date : LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit,
    onCloseDialog: () -> Unit
) {
    if (isOpenDialog) {
        Dialog(onDismissRequest = { onCloseDialog() }) {
            CustomDatePickerLayout(
                setLimit = setLimit,
                onNewDateSelected = onDateSelected,
                date = date
            )
        }
    }
}

/**
 * Layout for the custom date picker dialog.
 *
 * @param setLimit Optional parameter to limit selectable dates. Values can be "lower" for dates before today,
 * "upper" for dates after today, or null for no limit.
 * @param onNewDateSelected Callback triggered when a new date is selected.
 */
@Composable
fun CustomDatePickerLayout(
    setLimit: String? = null,
    onNewDateSelected: (LocalDate) -> Unit,
    date: LocalDate
) {
    var selectedDate by remember { mutableStateOf(date) }
    var currentMonth by remember { mutableStateOf(YearMonth.of(date.year, date.month)) }

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.background(WhiteCapiro).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MonthHeader(
                currentMonth = currentMonth,
                onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
                onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DateGrid(
                setLimit = setLimit,
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                onDateSelected = {
                    onNewDateSelected(it)
                    selectedDate = it
                }
            )
        }
    }
}

/**
 * Displays the month and year header with navigation arrows.
 *
 * @param currentMonth The currently displayed month and year.
 * @param onPreviousMonth Callback triggered when the previous month arrow is clicked.
 * @param onNextMonth Callback triggered when the next month arrow is clicked.
 */
@Composable
fun MonthHeader(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale("es", "ES"))} ${currentMonth.year}".replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            },
            color = GreenCapiro,
            style = getTypography().headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(horizontal = 4.dp)
                .clickable { onPreviousMonth() },
            painter = painterResource(id = R.drawable.flecha_izq),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(horizontal = 4.dp)
                .clickable { onNextMonth() },
            painter = painterResource(id = R.drawable.flecha_dere),
            contentDescription = null
        )
    }
}

/**
 * Displays a grid of dates for the selected month.
 *
 * @param currentMonth The currently displayed month and year.
 * @param selectedDate The currently selected date.
 * @param onDateSelected Callback triggered when a date is selected.
 * @param setLimit Optional parameter to limit selectable dates. Values can be "lower" for dates before today,
 * "upper" for dates after today, or null for no limit.
 */
@Composable
fun DateGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    setLimit: String? = null
) {
    val nowDate = LocalDate.now()
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = currentMonth.atDay(1).dayOfWeek.value % 7 // Sunday as 0

    Column {
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = GreenCapiro)
        Row {
            listOf("Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    style = getTypography().bodySmall,
                    color = GrayDarkCapiro
                )
            }
        }
        for (week in 0 until (daysInMonth + firstDayOfWeek) / 7 + 1) {
            Row {
                for (day in 0..6) {
                    val date = week * 7 + day - firstDayOfWeek + 1
                    if (date in 1..daysInMonth) {
                        val currentDate = LocalDate.of(currentMonth.year, currentMonth.month, date)
                        val isSelected = currentDate == selectedDate
                        val backgroundColor = if (isSelected) GreenSecondCapiro else Color.Transparent
                        val textColor = when (setLimit) {
                            "lower" -> if (currentDate.isBefore(nowDate)) Color.Gray else GreenCapiro
                            "upper" -> if (currentDate.isAfter(nowDate)) Color.Gray else GreenCapiro
                            else -> GreenCapiro
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                                .background(backgroundColor, CircleShape)
                                .clickable {
                                    if (textColor != Color.Gray) {
                                        onDateSelected(currentDate)
                                    }
                                }
                        ) {
                            DateCell(
                                date = currentDate,
                                isSelected = isSelected,
                                textColor = textColor
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

/**
 * Displays a single date cell in the date grid.
 *
 * @param date The date to be displayed.
 * @param isSelected Boolean flag to indicate if the date is selected.
 * @param textColor The color of the text to be displayed.
 */
@Composable
fun DateCell(date: LocalDate, isSelected: Boolean, textColor: Color) {
    Text(
        text = date.dayOfMonth.toString(),
        color = textColor,
        style = getTypography().bodySmall,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    )
}

@Preview
@Composable
private fun CustomDatePickerPreview() {
    CustomDatePicker(
        setLimit = "upper",
        isOpenDialog = true,
        onDateSelected = { },
        onCloseDialog = { }
    )
}
