

import android.content.res.Resources
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.capiro.composables.R

internal actual val customFont: FontFamily = FontFamily(
    Font(R.font.sf_ro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_bold, FontWeight.Bold)
)

@Composable
actual fun getTypography(): Typography {
    val configuration = LocalConfiguration.current
    return if (configuration.screenWidthDp <= 400) {
        Typography
    } else {
        TypographyLarge
    }
}

actual object TypographyProvider {
    actual val typography: Typography

    init {
        val configuration = Resources.getSystem().configuration
        typography = if (configuration.screenWidthDp <= 400) {
            Typography
        } else {
            TypographyLarge
        }
    }
}
