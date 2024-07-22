package com.capiro.capiroui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.dialogs.ListDialogCapiro
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.dialogs.ListTextFieldDialogCapiro
import com.capiro.composables.dialogs.OneOptionDialogCapiro
import com.capiro.composables.dialogs.SearchListDialogCapiro
import com.capiro.composables.dialogs.TwoOptionsDialogCapiro

@Preview(showBackground = true)
@Composable
fun TestDialog() {

    var isTheDialogOpenTwoOptions by remember { mutableStateOf(false) }
    var isTheDialogOpenOneOption by remember { mutableStateOf(false) }
    var isTheDialogOpenList by remember { mutableStateOf(false) }

    var isTheDialogOpenListSearch by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("text") }

    var isDialogOpenListTextField by remember { mutableStateOf(false) }
    var searchTextField by remember { mutableStateOf("text") }


    val context = LocalContext.current

    Column {
        ButtonCapiro(
            text = "Two Options",
            onClick = { isTheDialogOpenTwoOptions = !isTheDialogOpenTwoOptions })

        ButtonCapiro(
            text = "one Options",
            onClick = { isTheDialogOpenOneOption = !isTheDialogOpenOneOption })

        ButtonCapiro(text = "list", onClick = { isTheDialogOpenList = !isTheDialogOpenList })

        // SEARCH
        ButtonCapiro(
            text = "list search",
            onClick = { isTheDialogOpenListSearch = !isTheDialogOpenListSearch })

        // LIST TEXTFIELD
        ButtonCapiro(
            text = "list textfield",
            onClick = { isDialogOpenListTextField = !isDialogOpenListTextField })
    }


    ListTextFieldDialogCapiro(
        modifier = Modifier.padding(vertical = 80.dp),
        titleIdRes = R.string.app_name,
        headerIcon = Icons.Filled.Lock,
        rowIcon = Icons.Filled.Edit,
        isDialogOpenState = isDialogOpenListTextField,
        dataList = arrayListOf(
            "item 1", "item 2", "item 3", "item 4",
            "item 5",
            "item 6",
            "item 7",
            "item 8",
            "item 9",
            "item 10",
            "item 11",
            "item 12",
            "item 13",
            "item 14",
            "item 15",
            "item 16",
            "item 17",
            "item 18",
            "item 19",
            "item 20",
            "item 21",
            "item 22",
            "item 23",
            "item 24",
            "item 25",
            "item 26",
            "item 27",
            "item 28",
            "item 29",
            "item 30",
            "item 31",
            "item 32",
            "item 33",
            "item 34",
            "item 35",
            "item 36",
        ),
        text = searchTextField,
        onTextChanged = { searchTextField = it },
        onItemSelected = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
        onClose = {
            isDialogOpenListTextField = false
        }) {

    }

    TwoOptionsDialogCapiro(
        imaResource = R.drawable.siembralogo,
        boldText = "dolor",
        message = "message Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam",
        positiveButtonText = "Positive",
        negativeButtonText = "Negative",
        onPositiveButtonClickEvent = {
            Toast.makeText(context, "Positive", Toast.LENGTH_SHORT).show()
        },
        onNegativeButtonClickEvent = {
            Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show()
        },
        isDialogOpenState = isTheDialogOpenTwoOptions
    )

    OneOptionDialogCapiro(
        imaResource = R.drawable.siembralogo,
        boldText = "dolor",
        message = "message Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam",
        positiveButtonText = "Positive",
        onPositiveButtonClickEvent = {
            Toast.makeText(context, "Positive", Toast.LENGTH_SHORT).show()
        },
        isDialogOpenState = isTheDialogOpenOneOption
    )



    ListDialogCapiro(
        headerIcon = Icons.Filled.Lock,
        titleIdRes = R.string.app_name,
        allData = arrayOf(
            "item 1",
            "item 2",
            "item 3",
            "item 4",
            "item 5",
            "item 6",
            "item 7",
            "item 8",
            "item 9",
            "item 10",
            "item 11",
            "item 12",
            "item 13",
            "item 14",
            "item 15",
            "item 16",
            "item 17",
            "item 18",
            "item 19",
            "item 20",
            "item 21",
            "item 22",
            "item 23",
            "item 24",
            "item 25",
            "item 26",
            "item 27",
            "item 28",
            "item 29",
            "item 30",
            "item 31",
            "item 32",
            "item 33",
            "item 34",
            "item 35",
            "item 36",
            "item 37",
            "item 38",
            "item 39",
            "item 40",
            "item 41",
            "item 42",
            "item 43",
            "item 44",
            "item 45",
            "item 46",
            "item 47",
            "item 48",
            "item 49",
            "item 50",
            "item 51",
            "item 52",
            "item 53",
            "item 54",
            "item 55",
            "item 56",
            "item 57",
            "item 58",
            "item 59",
            "item 60",
            "item 61",
            "item 62",
            "item 63",
            "item 64",
            "item 65",
            "item 66",
            "item 67",
            "item 68",
            "item 69",
            "item 70",
            "item 71",
            "item 72",
            "item 73",
            "item 74",
            "item 75",
            "item 76",
            "item 77",
            "item 78",
            "item 79",
            "item 80",
            "item 81",
            "item 82",
            "item 83",
            "item 84",
            "item 85",
            "item 86",
            "item 87",
            "item 88",
            "item 89",
            "item 90",
            "item 91",
            "item 92",
            "item 93",
            "item 94",
            "item 95",
            "item 96",
            "item 97",
            "item 98",
            "item 99",
            "item 100"
        ),
        onSearchItemSelectedChangeState = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            isTheDialogOpenList = false
        },
        isTheDialogOpenState = isTheDialogOpenList,
        onCloseDialogEvent = { isTheDialogOpenList = false },
        rowIcon = Icons.Filled.Lock
    )

    SearchListDialogCapiro(
        searchText = searchText,
        onSearchTextChange = { searchText = it },
        titleIdRes = R.string.app_name,
        headerIcon = Icons.Filled.Star,
        rowIcon = Icons.Filled.Star,
        isTheDialogOpenState = isTheDialogOpenListSearch,
        allData = arrayOf("item 1", "item 2", "item 3"),
        onSearchItemSelectedChangeState = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            isTheDialogOpenListSearch = false
        },
        onCloseDialogEvent = { isTheDialogOpenListSearch = false }
    )

}