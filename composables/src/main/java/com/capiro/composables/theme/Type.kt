

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.capiro.composables.R

val customFont = FontFamily(
    Font(R.font.sf_ro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
private val Typography = Typography(
    // ********* BODY ************
    bodyLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    // ********* DISPLAY ************
    displayLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // ********* HEADLINE ************
    headlineLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    // ********* TITLE ************
    titleLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    // ********* LABEL ************
    labelLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    labelMedium = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    )
)

private val TypographyLarge = Typography.copy(
    bodyLarge = Typography.bodyLarge.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = Typography.bodyMedium.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodySmall = Typography.bodySmall.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    displayLarge = Typography.displayLarge.copy(
        fontSize = 40.sp,
        lineHeight = 48.sp
    ),
    displayMedium = Typography.displayMedium.copy(
        fontSize = 34.sp,
        lineHeight = 40.sp
    ),
    displaySmall = Typography.displaySmall.copy(
        fontSize = 28.sp,
        lineHeight = 32.sp
    ),
    headlineLarge = Typography.headlineLarge.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineMedium = Typography.headlineMedium.copy(
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    headlineSmall = Typography.headlineSmall.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleLarge = Typography.titleLarge.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleMedium = Typography.titleMedium.copy(
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    titleSmall = Typography.titleSmall.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    labelLarge = Typography.labelLarge.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    labelMedium = Typography.labelMedium.copy(
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    labelSmall = Typography.labelSmall.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp
    )
)

@Composable
fun getTypography(): Typography {
    val configuration = LocalConfiguration.current
    return if (configuration.screenWidthDp <= 400) {
        Typography
    } else {
        TypographyLarge
    }
}