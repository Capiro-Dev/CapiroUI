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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.capiro.composables.R
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import com.capiro.composables.theme.WhiteCapiro
import getTypography
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

/** [CustomDatePicker.setLimit] token: today is the earliest selectable day (no past days). */
const val DATE_LIMIT_LOWER = "lower"

/** [CustomDatePicker.setLimit] token: today is the latest selectable day (no future days). */
const val DATE_LIMIT_UPPER = "upper"

/**
 * Displays a custom date picker dialog. The dialog is shown when [isOpenDialog] is true.
 *
 * ### Limiting what can be picked
 * Pass [minDate] and/or [maxDate] for an explicit selectable window — either side may be null,
 * meaning "unbounded on that end". Everything outside the window is greyed out, is not clickable,
 * and the month arrows stop at the first/last month that still holds a selectable day, so the user
 * cannot wander off into a fully-disabled calendar.
 *
 * ```
 * // Only today and yesterday:
 * CustomDatePicker(
 *     minDate = LocalDate.now().minusDays(1),
 *     maxDate = LocalDate.now(),
 *     isOpenDialog = isOpen,
 *     date = selected,
 *     onDateSelected = { … },
 *     onCloseDialog = { … },
 * )
 * ```
 *
 * [setLimit] is the older, coarser API, kept so existing callers keep working: [DATE_LIMIT_LOWER]
 * pins the minimum to today and [DATE_LIMIT_UPPER] pins the maximum to today. An explicit
 * [minDate]/[maxDate] always wins over [setLimit] for the end it defines. If [minDate] is after
 * [maxDate] nothing is selectable — the caller is expected to pass a coherent range.
 *
 * @param setLimit Legacy coarse limit: [DATE_LIMIT_LOWER], [DATE_LIMIT_UPPER], or null for no limit.
 * @param isOpenDialog Boolean flag to control the visibility of the dialog.
 * @param date The initially-selected day; also decides which month opens first.
 * @param minDate Earliest selectable day (inclusive), or null for unbounded.
 * @param maxDate Latest selectable day (inclusive), or null for unbounded.
 * @param onDateSelected Callback triggered when a selectable date is tapped.
 * @param onCloseDialog Callback triggered when the dialog is dismissed.
 */
@Composable
fun CustomDatePicker(
    setLimit: String? = null,
    isOpenDialog: Boolean = false,
    date: LocalDate = LocalDate.now(),
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
    onDateSelected: (LocalDate) -> Unit,
    onCloseDialog: () -> Unit
) {
    if (isOpenDialog) {
        Dialog(onDismissRequest = { onCloseDialog() }) {
            CustomDatePickerLayout(
                setLimit = setLimit,
                onNewDateSelected = onDateSelected,
                date = date,
                minDate = minDate,
                maxDate = maxDate
            )
        }
    }
}

/**
 * Layout for the custom date picker dialog.
 *
 * @param setLimit Legacy coarse limit: [DATE_LIMIT_LOWER], [DATE_LIMIT_UPPER], or null for no limit.
 * @param onNewDateSelected Callback triggered when a new date is selected.
 * @param date The initially-selected day; also decides which month opens first.
 * @param minDate Earliest selectable day (inclusive), or null for unbounded.
 * @param maxDate Latest selectable day (inclusive), or null for unbounded.
 */
@Composable
fun CustomDatePickerLayout(
    setLimit: String? = null,
    onNewDateSelected: (LocalDate) -> Unit,
    date: LocalDate,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null
) {
    val lowerBound = resolveMinDate(minDate, setLimit)
    val upperBound = resolveMaxDate(maxDate, setLimit)

    var selectedDate by remember(date) { mutableStateOf(date) }
    var currentMonth by remember(date) { mutableStateOf(YearMonth.of(date.year, date.month)) }

    // The arrows stop where the range does: a month entirely outside [lowerBound, upperBound] holds
    // nothing to pick, so there is no reason to let the user page into it.
    val canGoPrevious = lowerBound == null ||
            !currentMonth.minusMonths(1).isBefore(YearMonth.from(lowerBound))
    val canGoNext = upperBound == null ||
            !currentMonth.plusMonths(1).isAfter(YearMonth.from(upperBound))

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
                onNextMonth = { currentMonth = currentMonth.plusMonths(1) },
                canGoPrevious = canGoPrevious,
                canGoNext = canGoNext
            )
            Spacer(modifier = Modifier.height(16.dp))
            DateGrid(
                setLimit = setLimit,
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                minDate = minDate,
                maxDate = maxDate,
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
 * @param canGoPrevious When false the previous arrow is dimmed and does nothing.
 * @param canGoNext When false the next arrow is dimmed and does nothing.
 */
@Composable
fun MonthHeader(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    canGoPrevious: Boolean = true,
    canGoNext: Boolean = true
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
                .alpha(if (canGoPrevious) 1f else DISABLED_ALPHA)
                .clickable(enabled = canGoPrevious) { onPreviousMonth() },
            painter = painterResource(id = R.drawable.flecha_izq),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(horizontal = 4.dp)
                .alpha(if (canGoNext) 1f else DISABLED_ALPHA)
                .clickable(enabled = canGoNext) { onNextMonth() },
            painter = painterResource(id = R.drawable.flecha_dere),
            contentDescription = null
        )
    }
}

/**
 * Displays a grid of dates for the selected month. Days outside [minDate]..[maxDate] (or outside
 * the window [setLimit] implies) are greyed out and ignore taps.
 *
 * @param currentMonth The currently displayed month and year.
 * @param selectedDate The currently selected date.
 * @param onDateSelected Callback triggered when a selectable date is tapped.
 * @param setLimit Legacy coarse limit: [DATE_LIMIT_LOWER], [DATE_LIMIT_UPPER], or null for no limit.
 * @param minDate Earliest selectable day (inclusive), or null for unbounded.
 * @param maxDate Latest selectable day (inclusive), or null for unbounded.
 */
@Composable
fun DateGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    setLimit: String? = null,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null
) {
    val lowerBound = resolveMinDate(minDate, setLimit)
    val upperBound = resolveMaxDate(maxDate, setLimit)
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = currentMonth.atDay(1).dayOfWeek.value % 7 // Sunday as 0
    // Ceiling division: exactly as many rows as the month needs, no trailing empty week.
    val weeksInMonth = (daysInMonth + firstDayOfWeek + 6) / 7

    Column {
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = GreenCapiro)
        Row {
            listOf("Do","Lu", "Ma", "Mi", "Ju", "Vi", "Sa" ).forEach { day ->
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
        for (week in 0 until weeksInMonth) {
            Row {
                for (day in 0..6) {
                    val date = week * 7 + day - firstDayOfWeek + 1
                    if (date in 1..daysInMonth) {
                        val currentDate = LocalDate.of(currentMonth.year, currentMonth.month, date)
                        val isSelectable = isDateSelectable(currentDate, lowerBound, upperBound)
                        val isSelected = currentDate == selectedDate
                        val backgroundColor =
                            if (isSelected && isSelectable) GreenSecondCapiro else Color.Transparent
                        val textColor = if (isSelectable) GreenCapiro else Color.Gray
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                                .background(backgroundColor, CircleShape)
                                .clickable(enabled = isSelectable) { onDateSelected(currentDate) }
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

/** How much the month arrows fade out once there is nothing left to page to. */
private const val DISABLED_ALPHA = 0.3f

/** Explicit [minDate] wins; otherwise [DATE_LIMIT_LOWER] means "today is the earliest day". */
private fun resolveMinDate(minDate: LocalDate?, setLimit: String?): LocalDate? =
    minDate ?: LocalDate.now().takeIf { setLimit == DATE_LIMIT_LOWER }

/** Explicit [maxDate] wins; otherwise [DATE_LIMIT_UPPER] means "today is the latest day". */
private fun resolveMaxDate(maxDate: LocalDate?, setLimit: String?): LocalDate? =
    maxDate ?: LocalDate.now().takeIf { setLimit == DATE_LIMIT_UPPER }

/** Both bounds are inclusive; a null bound means that end is open. */
private fun isDateSelectable(date: LocalDate, minDate: LocalDate?, maxDate: LocalDate?): Boolean =
    (minDate == null || !date.isBefore(minDate)) && (maxDate == null || !date.isAfter(maxDate))

@Preview
@Composable
private fun CustomDatePickerPreview() {
    CustomDatePicker(
        setLimit = DATE_LIMIT_UPPER,
        isOpenDialog = true,
        onDateSelected = { },
        onCloseDialog = { }
    )
}

@Preview
@Composable
private fun CustomDatePickerRangePreview() {
    // Only today and yesterday are pickable; every other day is greyed out and the arrows are dead.
    CustomDatePicker(
        isOpenDialog = true,
        date = LocalDate.now(),
        minDate = LocalDate.now().minusDays(1),
        maxDate = LocalDate.now(),
        onDateSelected = { },
        onCloseDialog = { }
    )
}
