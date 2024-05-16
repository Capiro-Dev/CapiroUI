package com.capiro.composables.athomic_composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ToastCapiro(messageResourceId: MutableState<Int?>){

    if (messageResourceId.value != null) {
        Toast.makeText(
            LocalContext.current,
            stringResource(messageResourceId.value!!),
            Toast.LENGTH_SHORT
        ).show()
        messageResourceId.value = null
    }
}

@Preview
@Composable
private fun ToastCapiroPreview() {
   // ToastCapiro(messageResourceId = R.string.)
}