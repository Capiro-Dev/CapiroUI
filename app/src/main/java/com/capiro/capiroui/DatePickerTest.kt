package com.capiro.capiroui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.dialogs.CustomDatePicker

@Preview(showBackground = true)
@Composable
fun DatePickerTest(){
   val isTheDialogOpen1 = remember { mutableStateOf(true) }
   val isTheDialogOpen2 = remember { mutableStateOf(true) }
   val isTheDialogOpen3 = remember { mutableStateOf(true) }

   val selectedDate1 = remember { mutableStateOf("") }
   val selectedDate2 = remember { mutableStateOf("") }
   val selectedDate3 = remember { mutableStateOf("") }




   val scrollState = rememberScrollState()
   Column(Modifier.verticalScroll(scrollState)){
      Text(text =  "selectedDate1 = ${selectedDate1.value}")
      Text(text =  "selectedDate2 = ${selectedDate2.value}")
      Text(text =  "selectedDate3 = ${selectedDate3.value}")

      Box(
         modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
         CustomDatePicker(
            setLimit = "lower",
            isOpenDialog = isTheDialogOpen1.value,
            onCloseDialog = {},
            onDateSelected = { date ->
               selectedDate1.value = date.toString()
                isTheDialogOpen1.value=false
            }
            )
      }

      Box(
         modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
         CustomDatePicker(
            setLimit = "upper",
            isOpenDialog = isTheDialogOpen2.value,
            onCloseDialog = {},
            onDateSelected = { date ->
               selectedDate2.value = date.toString()
               isTheDialogOpen2.value=false
            })
      }

      Box(
         modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
         CustomDatePicker(
            isOpenDialog = isTheDialogOpen3.value,
            onCloseDialog = {},
            onDateSelected = { date ->
               selectedDate3.value = date.toString()
               isTheDialogOpen3.value=false
            }
         )
      }
   }
}