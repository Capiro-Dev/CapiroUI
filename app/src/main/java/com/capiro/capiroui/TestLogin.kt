package com.capiro.capiroui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.screens.LoginScreenCapiro
import com.capiro.composables.R.drawable.test_app_logo

@Preview(showBackground = true)
@Composable
fun Login() {
    val context = StableWrapper(LocalContext.current)

    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }
    LoginScreenCapiro(
        iconAppResource = test_app_logo,
        onBackUpClick = {Toast.makeText(
            context.value,
            "click",
            Toast.LENGTH_SHORT
        ).show()},
        user = user.value,
        password = password.value,
        errorMessage = errorMessage.value,
        onUserTextChange = { user.value = it },
        onPasswordTextChange = { password.value = it },
        onLoginClick = {
            errorMessage.value = "Usuario o contraseña incorrectos"
        }
    )

}

@Stable
data class StableWrapper<T>(val value: T)