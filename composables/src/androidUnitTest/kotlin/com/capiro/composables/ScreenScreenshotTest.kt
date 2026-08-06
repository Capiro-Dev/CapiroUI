package com.capiro.composables

import androidx.compose.ui.res.painterResource
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode
import com.capiro.composables.screens.LoginScreenCapiro
import com.capiro.composables.screens.SplashScreenCapiro
import com.capiro.composables.util_composables.ToolbarMenuLayoutDialogCapiro
import org.junit.Rule
import org.junit.Test

/**
 * Renders full-screen library composables to PNG snapshots at full device size.
 * Run `./gradlew :composables:recordPaparazziDebug` to (re)generate the images.
 */
class ScreenScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        renderingMode = RenderingMode.NORMAL,
        showSystemUi = false,
    )

    @Test fun splash_screen_capiro() {
        paparazzi.snapshot {
            SplashScreenCapiro(icon = painterResource(id = R.drawable.test_app_logo), version = "1.0.0")
        }
    }

    @Test fun login_screen_capiro() {
        paparazzi.snapshot {
            LoginScreenCapiro(
                icon = painterResource(id = R.drawable.test_app_logo),
                onBackUpClick = {},
                user = "danilo.osorio",
                password = "secret123",
                onUserTextChange = {},
                onPasswordTextChange = {},
                onLoginClick = {},
            )
        }
    }

    @Test fun account_menu_dialog() {
        paparazzi.snapshot {
            ToolbarMenuLayoutDialogCapiro(
                user = "Danilo Osorio",
                onLogoutClick = {},
                onBackUpClick = {},
                onPrintersClick = {},
                isOpenDialog = true,
                onCloseDialog = {},
            )
        }
    }
}
