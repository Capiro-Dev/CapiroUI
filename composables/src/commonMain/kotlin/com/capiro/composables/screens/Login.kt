package com.capiro.composables.screens

import TypographyProvider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.buttons.ButtonIconCapiro
import com.capiro.composables.athomic_composables.image.ImageCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldAndTileRoundedBorderCapiro
import com.capiro.composables.resources.Res
import com.capiro.composables.resources.login_bt_back_up
import com.capiro.composables.resources.login_bt_start_session
import com.capiro.composables.resources.login_tf_password_label
import com.capiro.composables.resources.login_tf_user_label
import com.capiro.composables.resources.login_tt_password_label
import com.capiro.composables.resources.login_tt_user_label
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.ErrorCapiro
import org.jetbrains.compose.resources.stringResource

/**
 * Displays the login screen with an app icon, user and password fields, a backup button, and a login button.
 *
 * @param icon The [Painter] for the app icon to be displayed.
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
    icon: Painter,
    onBackUpClick: () -> Unit,
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
        icon = icon,
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
 * @param icon The [Painter] for the app icon to be displayed.
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
    icon: Painter,
    errorMessage: String? = null,
    onStartBackUpProcessEvent: () -> Unit,
    onUserTextEvent: (String) -> Unit,
    onUserPasswordEvent: (String) -> Unit,
    onLoginButtonClicked: () -> Unit
) {

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
                onClick = { onStartBackUpProcessEvent() },
                modifier = Modifier.size(32.dp),
                label = stringResource(Res.string.login_bt_back_up)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // ICON APP
            ImageCapiro(
                painter = icon,
                modifier = Modifier.height(60.dp)
            )

            // USER
            TextFieldAndTileRoundedBorderCapiro(
                onTextChangeEvent = { onUserTextEvent(it) },
                textInput = userState,
                label = stringResource(Res.string.login_tf_user_label),
                title = stringResource(Res.string.login_tt_user_label),
                errorMessage = null,
                imeAction = ImeAction.Next,
                lines = 1
            )

            // PASSWORD
            TextFieldAndTileRoundedBorderCapiro(
                onTextChangeEvent = { onUserPasswordEvent(it) },
                textInput = passwordState,
                label = stringResource(Res.string.login_tf_password_label),
                title = stringResource(Res.string.login_tt_password_label),
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
                    .fillMaxWidth(),
                textStyle = TypographyProvider.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                text = stringResource(Res.string.login_bt_start_session),
                onClick = { onLoginButtonClicked() }
            )

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
private fun LoginScreenCapiroPreview() {
    val text = remember { mutableStateOf("") }
    LoginScreenCapiro(
        icon = ColorPainter(Color.Gray),
        onBackUpClick = { },
        user = text.value,
        password = "ssss",
        onUserTextChange = { text.value = it },
        onPasswordTextChange = { },
        onLoginClick = { }
    )
}
