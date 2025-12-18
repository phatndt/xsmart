package my.xsmart.feature.salarycalculator.ui.input.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import my.xsmart.share.ui.theme.AppTypography
import my.xsmart.share.ui.widget.XSmartRadioOptionCardDefault


val Primary = Color(0xFF0D7FF2)
val OnPrimary = Color(0xFFFFFFFF)

val PrimaryContainer = Color(0xFFB5D7FB)
val OnPrimaryContainer = Color(0xFF10182B)

val Secondary = Color(0xFF7FBBF8)
val OnSecondary = Color(0xFF10182B)

val Background = Color(0xFFF5F7F8)
val OnBackground = Color(0xFF10182B)

val Surface = Color(0xFFFFFFFF)
val OnSurface = Color(0xFF10182B)

val Outline = Color(0xFF8994A5)

val Tertiary = Color(0xFF4A5669)
val OnTertiary = Color(0xFFFFFFFF)

/* ---------- Secondary container ---------- */
val SecondaryContainer = Color(0xFFDCEBFD)
val OnSecondaryContainer = Color(0xFF10182B)

/* ---------- Tertiary container ---------- */
val TertiaryContainer = Color(0xFFE3E6EA)
val OnTertiaryContainer = Color(0xFF10182B)

/* ---------- Surface variant ---------- */
val SurfaceVariant = Color(0xFFE9ECF0)
val OnSurfaceVariant = Color(0xFF4A5669)

/* ---------- Error ---------- */
val Error = Color(0xFFBA1A1A)
val OnError = Color(0xFFFFFFFF)
val ErrorContainer = Color(0xFFFFDAD6)
val OnErrorContainer = Color(0xFF410002)

/* ---------- Inverse ---------- */
val InverseSurface = Color(0xFF10182B)
val InverseOnSurface = Color(0xFFF5F7F8)
val InversePrimary = Color(0xFF7FBBF8)

/* ---------- Misc ---------- */
val SurfaceTint = Primary
val OutlineVariant = Color(0xFFD1D6DE)
val Scrim = Color(0x66000000)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,

    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,

    background = Background,
    onBackground = OnBackground,

    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceTint = SurfaceTint,

    outline = Outline,
    outlineVariant = OutlineVariant,

    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,
    inversePrimary = InversePrimary,

    scrim = Scrim
)

@Composable
fun SalaryCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) LightColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content,
    )
}

object SalaryCalculatorTextFieldDefault {

    @Composable
    fun color(
        focusedContainerColor: Color = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor: Color = MaterialTheme.colorScheme.surface,
        disabledContainerColor: Color = MaterialTheme.colorScheme.surface,
        errorContainerColor: Color = MaterialTheme.colorScheme.surface,
        focusedBorderColor: Color = Color.Transparent,
        unfocusedBorderColor: Color = Color.Transparent,
    ) = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        disabledContainerColor = disabledContainerColor,
        errorContainerColor = errorContainerColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
    )

    @Composable
    fun color() = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        disabledContainerColor = MaterialTheme.colorScheme.surface,
        errorContainerColor = MaterialTheme.colorScheme.surface,
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
    )
}
