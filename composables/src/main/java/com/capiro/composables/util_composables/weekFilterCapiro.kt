package com.capiro.composables.util_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.athomic_composables.checkbox.CheckBoxCapiro
import com.capiro.composables.theme.GreenCapiro

@Composable
fun WeekFilterCapiro(
    daysChecked: Map<String, Boolean>,
    onDayCheckedChange: (String, Boolean) -> Unit
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        daysChecked.forEach { (day, isChecked) ->
            ColumnCheckBox(day, isChecked) { onDayCheckedChange(day, it) }
        }
    }
}

@Composable
fun ColumnCheckBox(day: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = day, color = GreenCapiro)
        CheckBoxCapiro(
            isChecked = isChecked,
            onCheckedChange = onCheckedChange,
            scale = 1f,
            isEnabled = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestWeekFilter() {
    val daysState = remember {
        mutableStateOf(
            mapOf(
                "L" to false, "M" to false, "W" to false,
                "J" to false, "V" to false, "S" to false, "D" to false
            )
        )
    }
    WeekFilterCapiro(
        daysChecked = daysState.value,
        onDayCheckedChange = { day, isChecked ->
            daysState.value = daysState.value.toMutableMap().apply {
                this[day] = isChecked
            }
        }
    )
}
