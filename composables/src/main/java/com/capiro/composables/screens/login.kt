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
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.buttons.ButtonIconCapiro
import com.capiro.composables.athomic_composables.image.ImageCapiro

import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.textfield.TextFieldAndTileRoundedBorderCapiro
import com.capiro.composables.theme.ErrorCapiro

/**
 * Displays the login screen with an app icon, user and password fields, a backup button, and a login button.
 *
 * @param iconAppResource The drawable resource ID of the app icon to be displayed.
 * @param onBackUpClick Callback to be invoked when the backup button is clicked.
 * @param user The current state of the user text field.
 * @param password The current state of the password text field.
 * @param errorMessage Optional error message to be displayed.
 * @param onUserTextChange Callback to be invoked when the user text changes.
 * @param onPasswordTextChange Callback to be invoked when the password text changes.
 * @param onLoginClick Callback to be invoked when the login button is clicked.
 */
@Composable
fun LoginScreenCapiro(
    @DrawableRes iconAppResource: Int,
    onBackUpClick: (Context) -> Unit,
    user: String,
    password: String,
    errorMessage: String? = null,
    onUserTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    ContentSection(
        userState = user,
        passwordState = password,
        iconAppResource = iconAppResource,
        errorMessage = errorMessage,
        onStartBackUpProcessEvent = onBackUpClick,
        onUserTextEvent = onUserTextChange,
        onUserPasswordEvent = onPasswordTextChange,
        onLoginButtonClicked = onLoginClick
    )
}

/**
 * Displays the content section of the login screen including user and password text fields,
 * a backup button, and a login button.
 *
 * @param userState The current state of the user text field.
 * @param passwordState The current state of the password text field.
 * @param iconAppResource The drawable resource ID of the app icon to be displayed.
 * @param errorMessage Optional error message to be displayed.
 * @param onStartBackUpProcessEvent Callback to be invoked when the backup button is clicked.
 * @param onUserTextEvent Callback to be invoked when the user text changes.
 * @param onUserPasswordEvent Callback to be invoked when the password text changes.
 * @param onLoginButtonClicked Callback to be invoked when the login button is clicked.
 */
@Composable
private fun ContentSection(
    userState: String,
    passwordState: String,
    iconAppResource: Int,
    errorMessage: String? = null,
    onStartBackUpProcessEvent: (Context) -> Unit,
    onUserTextEvent: (String) -> Unit,
    onUserPasswordEvent: (String) -> Unit,
    onLoginButtonClicked: () -> Unit
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
                label = stringResource(id = R.string.login_tf_user_label),
                title = stringResource(id = R.string.login_tt_user_label),
                errorMessage = null,
                imeAction = ImeAction.Next,
                lines = 1
            )

            // PASSWORD
            TextFieldAndTileRoundedBorderCapiro(
                onTextChangeEvent = { onUserPasswordEvent(it) },
                textInput = passwordState,
                label = stringResource(id = R.string.login_tf_password_label),
                title = stringResource(id = R.string.login_tt_password_label),
                isPassword = true,
                errorMessage = null,
                imeAction = ImeAction.Done,
                lines = 1
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.height(16.dp),
                    text = errorMessage ?: "",
                    color = ErrorCapiro,
                    style = typography.bodySmall
                )
            }

            // LOGIN BUTTON
            ButtonCapiro(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.login_bt_start_session),
                onClick = { onLoginButtonClicked() }
            )

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
private fun LoginScreenCapiroPreview() {
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