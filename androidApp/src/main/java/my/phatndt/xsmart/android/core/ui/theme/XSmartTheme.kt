package my.phatndt.xsmart.android.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.phatndt.xsmart.android.R

@Composable
fun XSmartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF332966),
            primaryVariant = Color(0xFFfe693c),
            secondary = Color(0xFF46afff)
        )
    }

    val fontName =
        FontFamily(
            Font(R.font.roboto_thin, weight = FontWeight.W100, style = FontStyle.Normal),
            Font(R.font.roboto_light, weight = FontWeight.W300, style = FontStyle.Normal),
            Font(R.font.roboto_regular, weight = FontWeight.W400, style = FontStyle.Normal),
            Font(R.font.roboto_medium, weight = FontWeight.W500, style = FontStyle.Normal),
            Font(R.font.roboto_bold, weight = FontWeight.W700, style = FontStyle.Normal),
        )

    val typography = Typography(
        h1 = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.W700,
            fontSize = 32.sp
        ),
        h2 = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp
        ),
        h3 = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp
        ),
        h4 = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp
        ),
        h5 = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.W700,
            fontSize = 12.sp
        ),
        h6 = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.W700,
            fontSize = 8.sp
        ),
        body1 = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        caption = TextStyle(
            fontFamily = fontName,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        ),
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(colors = colors, typography = typography, shapes = shapes, content = content)
}

fun solution(inputString: String): Boolean {
    for (i in 0..inputString.length / 2) {
        if (inputString[i] != inputString[inputString.length - i]) {
            return false
        }
    }
    return true
}