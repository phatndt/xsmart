package my.phatndt.xsmart.android.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import my.phatndt.xsmart.android.R

val fontName =
    FontFamily(
        Font(R.font.roboto_thin, weight = FontWeight.W100, style = FontStyle.Normal),
        Font(R.font.roboto_light, weight = FontWeight.W300, style = FontStyle.Normal),
        Font(R.font.roboto_regular, weight = FontWeight.W400, style = FontStyle.Normal),
        Font(R.font.roboto_medium, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(R.font.roboto_bold, weight = FontWeight.W700, style = FontStyle.Normal),
    )

object XSmartTextStyles {
    val h1 = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.W700,
        fontSize = 32.sp,
    )
    val h2 = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
    )
    val h3 = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
    )
    val h4 = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
    )
    val h5 = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.W700,
        fontSize = 12.sp,
    )
    val h6 = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.W700,
        fontSize = 8.sp,
    )
    val body1 = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
    val caption = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    )
    val button = TextStyle(
//        fontFamily = fontName,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    )
}
