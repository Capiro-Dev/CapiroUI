package com.capiro.capiroui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.athomic_composables.ToastCapiro
import com.capiro.composables.screens.LoginScreenCapiro
import com.capiro.composables.R.drawable.test_app_logo

@Preview(showBackground = true)
@Composable
fun Login() {
    val toast = Toast.makeText(
        LocalContext.current,
        "click",
        Toast.LENGTH_SHORT
    )
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }
    LoginScreenCapiro(
        iconAppResource = test_app_logo,
        onBackUpClick = {toast.show()},
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