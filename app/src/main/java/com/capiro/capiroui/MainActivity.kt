package com.capiro.capiroui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.capiroui.ui.theme.CapiroUITheme
import com.capiro.composables.athomic_composables.textfield.TextFieldAndTileRoundedBorderCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldOutlinedCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldRoundedBorderCapiro

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapiroUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CapiroUITheme {

                   TextFields()

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFields() {
    //scrollableColumn
    val scrollState = rememberScrollState()
    val text = remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .verticalScroll(scrollState)) {
        TextFieldCapiro(
            textInput = text.value,
            label = "TextFieldCapiro",
            onTextChangeEvent = { text.value = it}
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextFieldOutlinedCapiro(
            textInput = text.value,
            label = "TextFieldOutlinedCapiro",
            onTextChangeEvent = { text.value = it}
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextFieldRoundedBorderCapiro(
            textInput = text.value,
            onTextChangeEvent = { text.value = it},
            label = "TextFieldRoundedBorderCapiro" )
        Spacer(modifier = Modifier.size(16.dp))
        TextFieldAndTileRoundedBorderCapiro(
            textInput =text.value ,
            onTextChangeEvent = { text.value = it},
            label = "TextFieldAndTileRoundedBorderCapiro",
            title = "title",
        )
    }
}



