package com.capiro.capiroui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.util_composables.CalendarBarCapiro


@Preview
@Composable
fun CalendarBarTest(){
    Box(modifier = Modifier.background(BeigeCapiro).fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        CalendarBarCapiro(
            23.toString(),
            dayOfWeek = "Jueves",
            month = "Agosto",
            onCalendarClick = {}
        )
    }
}