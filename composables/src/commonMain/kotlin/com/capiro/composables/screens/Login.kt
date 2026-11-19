package com.capiro.composables.screens

import TypographyProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
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
 *
 * Keyboard-aware: the form stays above the on-screen keyboard. The screen sizes itself to the
 * space left above the IME ([imePadding]), scrolls when that space is shorter than the form needs
 * ([LOGIN_MIN_HEIGHT]) and, while the keyboard is open, scrolls to the bottom so both fields and
 * the login button stay visible (only the backup button and logo scroll out). This matters for
 * edge-to-edge apps, where Android no longer resizes the window for the keyboard. Insets the host
 * already consumed (e.g. `consumeWindowInsets` on a Scaffold's padding) are not counted twice.
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
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(BeigeCapiro)
            .imePadding()
    ) {
        val scrollState = rememberScrollState()
        // `WindowInsets.isImeVisible` is Android-only; the IME inset itself is multiplatform.
        val isKeyboardOpen = WindowInsets.ime.getBottom(LocalDensity.current) > 0

        // The IME inset animates in, so the scroll range grows over several frames: follow it while
        // the keyboard is open. Only range changes re-pin the scroll, so manual scrolling still works.
        LaunchedEffect(isKeyboardOpen) {
            if (isKeyboardOpen) {
                snapshotFlow { scrollState.maxValue }.collect { scrollState.scrollTo(it) }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .height(max(maxHeight, LOGIN_MIN_HEIGHT))
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
    }
}

/**
 * Height [ContentSection] needs (backup row + logo + two fields + error line + button + paddings)
 * before it starts clipping; below it the screen scrolls instead.
 */
private val LOGIN_MIN_HEIGHT = 520.dp

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
