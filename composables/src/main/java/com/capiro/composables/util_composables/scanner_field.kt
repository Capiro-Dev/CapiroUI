package com.capiro.composables.util_composables

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A composable function that displays a simple text field and processes the input using the provided `scanProcess` function.
 *
 * @param scanProcess A lambda function that takes a `String` as input. This function is called when the user enters text.
 */
@Composable
fun ScannerFieldCapiro(
    scanProcess: (String) -> Unit
) {

    // Controller to manage the software keyboard.
    val keyboardController = LocalSoftwareKeyboardController.current
    // Manages the focus of the text field.
    val focusRequester = remember { FocusRequester() }
    // Tracks whether the text field is currently focused.
    val focus = remember { mutableStateOf(false) }
    // Ensures that the scan process is executed only once per valid input.
    val firstExecute = remember { mutableStateOf(true) }
    // Manages coroutine scope within the composable.
    val coroutineScope = rememberCoroutineScope()

    // Basic text field that captures user input.
    BasicTextField(
        value = "",
        onValueChange = { newText ->
            // If this is the first execution and the input is not empty.
            if (firstExecute.value && newText.isNotEmpty()) {
                // Trigger the scan process with the new text.
                scanProcess(newText)
                firstExecute.value = false

                // Reset the firstExecute flag after a short delay.
                coroutineScope.launch(Dispatchers.Main) {
                    delay(50)
                    firstExecute.value = true
                }
            }
        },
        modifier = Modifier
            .size(1.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (focus.value != it.isFocused) {
                    focus.value = it.isFocused
                    if (!it.isFocused) {
                        keyboardController?.hide()
                    }
                }
            }
    )

    // Auto-focuses the text field and hides the keyboard when the composable is first launched.
    LaunchedEffect("") {
        focusRequester.requestFocus()
        keyboardController?.hide()
        delay(10)
        keyboardController?.hide()
    }
}


@Preview
@Composable
private fun PreviewScannerFieldCapiro() {
    ScannerFieldCapiro{
        Log.wtf("SimpleTextaaField", it)
    }
}