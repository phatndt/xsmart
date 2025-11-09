package my.xsmart.share.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

@Composable
fun XSmartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFFFFFFF),
            secondary = Color(0xFF03DAC5),
        )
    } else {
        lightColorScheme(
            background = Color(0xFFFFFFFF),
            primary = Color(0xFF332966),
            secondary = Color(0xFF46afff),
        )
    }

    val typography = MaterialTheme.typography

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp),
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

   MaterialTheme(
       colorScheme = colorScheme,
       typography = typography,
       shapes = shapes,
       content = content
   )
}

fun solution(inputString: String): Boolean {
    for (i in 0..inputString.length / 2) {
        if (inputString[i] != inputString[inputString.length - i]) {
            return false
        }
    }
    return true
}
