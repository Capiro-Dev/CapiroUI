package com.capiro.composables.screens

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.athomic_composables.ButtonIconCapiro
import com.capiro.composables.athomic_composables.ImageCapiro

import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.R
import com.capiro.composables.textfield.TextFieldAndTileRoundedBorderCapiro
import com.capiro.composables.theme.ErrorCapiro

@Composable
fun LoginScreenCapiro(

    @DrawableRes iconAppResource: Int,
    onBackUpClick: (Context) -> Unit,
    user: String,
    password: String,
    errorMessage: String? = null,
    onUserTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onLoginClick: () -> Unit,

    ) {

    ContentSection(
        userState = user,
        passwordState = password,
        onStartBackUpProcessEvent = onBackUpClick,
        errorMessage = errorMessage,
        onUserTextEvent = onUserTextChange,
        iconAppResource = iconAppResource,
        onUserPasswordEvent = onPasswordTextChange,
        onLoginButtonClicked = {
            onLoginClick()
        }
    )

}


// ********************************++*** CONTENT SECTION *******************************************

@Composable
private fun ContentSection(
    userState: String,
    passwordState: String,
    iconAppResource: Int,
    errorMessage: String? = null,
    onStartBackUpProcessEvent: (Context) -> Unit,
    onUserTextEvent: (String) -> Unit,
    onUserPasswordEvent: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BeigeCapiro)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            ButtonIconCapiro(
                image = Icons.Filled.DownloadForOffline,
                onClick = { onStartBackUpProcessEvent(context) },
                modifier = Modifier.size(32.dp),
                label = stringResource(id = R.string.login_bt_back_up)
            )

        }


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // ICON APP
            ImageCapiro(
                imageSourceId = iconAppResource,
                modifier = Modifier.height(60.dp)
            )

            // USER
            TextFieldAndTileRoundedBorderCapiro(
                onTextChangeEvent = { onUserTextEvent(it) },
                textInput = userState,
                leadingIcon = Icons.Filled.Person,
                label = stringResource(id = R.string.login_tf_user_label),
                title = stringResource(id = R.string.login_tt_user_label),
                isNumeric = false,
                errorMessage = null,
                imeAction = ImeAction.Next,
                lines = 1
            )

            // PASSWORD
            TextFieldAndTileRoundedBorderCapiro(
                onTextChangeEvent = { onUserPasswordEvent(it) },
                textInput = passwordState,
                leadingIcon = Icons.Filled.Lock,
                label = stringResource(id = R.string.login_tf_password_label),
                title = stringResource(id = R.string.login_tt_password_label),
                isNumeric = false,
                isPassword = true,
                errorMessage = null,
                imeAction = ImeAction.Done,
                lines = 1
            )
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(modifier=Modifier.height(16.dp), text = errorMessage ?: "", color = ErrorCapiro, style = typography.bodySmall)
            }

            // LOGIN BUTTON
            ButtonCapiro(
                modifier= Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.login_bt_start_session),
                onClick = {
                    onLoginButtonClicked()
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

        }


    }


}


@Preview
@Composable
private fun LoginScreenCapiroPreview() {
    Box {
        Text(text = "Select an item")

    }

    LoginScreenCapiro(
        iconAppResource = R.drawable.test_app_logo,
        onBackUpClick = { },
        user = "",
        password = "ssss",
        onUserTextChange = { },
        onPasswordTextChange = { },
        onLoginClick = { }
    )
}