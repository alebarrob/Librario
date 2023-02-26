package barrera.alejandro.librario.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import barrera.alejandro.librario.R

private val fontFamilyLato = FontFamily(
    listOf(
        Font(
            resId = R.font.lato_regular,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.lato_bold,
            weight = FontWeight.Bold
        )
    )
)

private val fontFamilyAmaticSC = FontFamily(
    listOf(
        Font(
            resId = R.font.amaticsc_regular,
            weight = FontWeight.Medium
        ),
        Font(
            resId = R.font.amaticsc_bold,
            weight = FontWeight.Bold
        )
    )
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fontFamilyAmaticSC,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
        lineHeight = 24.sp,
        letterSpacing = 2.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontFamilyAmaticSC,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamilyAmaticSC,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 27.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamilyAmaticSC,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamilyAmaticSC,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
        lineHeight = 25.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)