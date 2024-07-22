package com.capiro.composables.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.capiro.composables.athomic_composables.CardCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import getTypography
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Composable
fun CustomDatePicker(
    setLimit: String? = null,
    isOpenDialog : Boolean = false,
    onDateSelected: (LocalDate) -> Unit ,
    onCloseDialog: () -> Unit
) {


    if (isOpenDialog) {
        Dialog(onDismissRequest = { onCloseDialog() }) {
            CustomDatePickerLayout(
                setLimit = setLimit,
                onNewDateSelected = onDateSelected
            )
        }
    }
}

@Composable
fun CustomDatePickerLayout(
    setLimit: String? = null,
    onNewDateSelected: (LocalDate) -> Unit

) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    CardCapiro(paddingInner = 8.dp,
        innerComposable = {

            Column(
                modifier = Modifier.padding(16.dp),
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
                        selectedDate = it }
                )
            }
        }
    )
}

@Composable
fun MonthHeader(currentMonth: YearMonth, onPreviousMonth: () -> Unit, onNextMonth: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {

        // TITLE
        Text(
            modifier = Modifier.weight(1f),
            text = "${
                currentMonth.month.getDisplayName(
                    TextStyle.FULL,
                    Locale("es", "ES")
                )
            } ${currentMonth.year}".replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            },
            color = GreenCapiro,
            style = getTypography().headlineLarge,
            fontWeight = FontWeight.Bold
        )

        // ARROW LEFT ARROW
        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(horizontal = 4.dp)
                .clickable { onPreviousMonth() },
            painter = painterResource(id = R.drawable.flecha_izq),
            contentDescription = null
        )

        // ARROW RIGHT ARROW
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

@Composable
fun DateGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    setLimit: String? = null,
) {

    val nowDate = LocalDate.now()
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = currentMonth.atDay(1).dayOfWeek.value % 7 // Sunday as 0
    val typo = getTypography()

    Column {
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = GreenCapiro)
        Row {
            listOf("Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    style = typo.bodySmall,
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
                        val backgroundColor =
                            if (isSelected) GreenSecondCapiro else Color.Transparent
                        var textColor = if (isSelected) Color.White else GreenCapiro

                        when(setLimit){
                            "lower" -> {
                                if (currentDate.isBefore(nowDate)) textColor = Color.Gray
                            }
                            "upper" -> {
                                if (currentDate.isAfter(nowDate)) textColor = Color.Gray
                            }
                            else -> {textColor = GreenCapiro}
                        }
                        //if (currentDate.isBefore(nowDate)) textColor = Color.Gray
                       // if (lowerLimit != null && currentDate.isBefore(lowerDateLimit)) textColor = Color.Gray
                        //if (upperLimit != null && currentDate.isAfter(upperDateLimit)) textColor = Color.Gray

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                                .background(backgroundColor, CircleShape)
                                .clickable {
                                    when(setLimit){
                                        "lower" -> {
                                            if (currentDate.isBefore(nowDate)) return@clickable
                                        }
                                        "upper" -> {
                                            if (currentDate.isAfter(nowDate)) return@clickable
                                        }
                                    }
                                   // if (lowerLimit != null && currentDate.isBefore(lowerDateLimit)) return@clickable
                                   // if (upperLimit != null && currentDate.isAfter(upperDateLimit)) return@clickable
                                    onDateSelected(currentDate)
                                }
                        ) {
                            DateCell(
                                date = currentDate,
                                isSelected = currentDate == selectedDate,
                                textColor
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

@Composable
fun DateCell(date: LocalDate, isSelected: Boolean, textColor: Color) {

    val typo = getTypography()

    Text(
        text = date.dayOfMonth.toString(),
        color = textColor,
        style = typo.bodySmall,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    )

}

