package com.capiro.capiroui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.athomic_composables.ButtonCapiro

@Preview(showBackground = true)
@Composable
fun TestButtons() {
    val toast = Toast.makeText(
        LocalContext.current,
        "click",
        Toast.LENGTH_SHORT
    )
    //scrollableColumn
    val scrollState = rememberScrollState()
    //val text = remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().verticalScroll(scrollState)){
        ButtonCapiro(
            text = "ButtonCapiro",
            onClick = {
                Log.d("ButtonCapiro", "ButtonCapiro clicked")
                toast.show()
            }
        )
    }
}
