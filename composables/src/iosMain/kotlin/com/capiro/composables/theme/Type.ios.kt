

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

// TODO: load the real SF Pro Display font via Compose Multiplatform resources
// (commonMain/composeResources/font/) instead of falling back to the system font.
internal actual val customFont: FontFamily = FontFamily.Default

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getTypography(): Typography {
    val screenWidthDp = UIScreen.mainScreen.bounds.useContents { size.width }
    return if (screenWidthDp <= 400.0) {
        Typography
    } else {
        TypographyLarge
    }
}

@OptIn(ExperimentalForeignApi::class)
actual object TypographyProvider {
    actual val typography: Typography

    init {
        val screenWidthDp = UIScreen.mainScreen.bounds.useContents { size.width }
        typography = if (screenWidthDp <= 400.0) {
            Typography
        } else {
            TypographyLarge
        }
    }
}
