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

// TailwindColors.kt

/* ---------- Brand ---------- */
val twPrimary = Color(0xFF0D7FF2)
val twPrimarySoft = Color(0xFF7FBBF8)
val twPrimaryContainer = Color(0xFFB5D7FB)

/* ---------- Background ---------- */
val twBackgroundLight = Color(0xFFF5F7F8)
val twBackgroundDark = Color(0xFF101922)
val twCardDark = Color(0xFF1C2A38)

/* ---------- Slate / Neutral ---------- */
val twSlate900 = Color(0xFF10182B)
val twSlate800 = Color(0xFF1E293B)
val twSlate700 = Color(0xFF334155)
val twSlate600 = Color(0xFF4A5669)
val twSlate500 = Color(0xFF64748B)
val twSlate400 = Color(0xFF8994A5)
val twSlate300 = Color(0xFFCBD5E1)
val twSlate200 = Color(0xFFD1D6DE)
val twSlate100 = Color(0xFFE9ECF0)

/* ---------- Accent ---------- */
val twRose = Color(0xFFBA1A1A)

/* ---------- Result Screen Colors ---------- */
// Teal colors for insurance
val Teal500 = Color(0xFF14B8A6)
val Teal600 = Color(0xFF0D9488)
val Teal400 = Color(0xFF2DD4BF)

// Rose colors for tax
val Rose700 = Color(0xFFBE123C)
val Rose600 = Color(0xFFE11D48)
val Rose500 = Color(0xFFF43F5E)
val Rose400 = Color(0xFFFB7185)
val Rose100 = Color(0xFFFFE4E6)

// Indigo colors for deductions
val Indigo500 = Color(0xFF6366F1)

/* ---------- Primary ---------- */
val Primary: Color = twPrimary
val OnPrimary: Color = Color.White

val PrimaryContainer: Color = twPrimaryContainer
val OnPrimaryContainer: Color = twSlate900

/* ---------- Secondary ---------- */
val Secondary: Color = Teal500
val OnSecondary: Color = Color.White

val SecondaryContainer: Color = twPrimarySoft.copy(alpha = 0.35f)
val OnSecondaryContainer: Color = twSlate900

/* ---------- Tertiary ---------- */
val Tertiary: Color = Rose500
val OnTertiary: Color = Color.White

val TertiaryContainer: Color = twSlate100
val OnTertiaryContainer: Color = twSlate900

/* ---------- Background / Surface ---------- */
val Background: Color = twBackgroundLight
val OnBackground: Color = twSlate900

val Surface: Color = Color.White
val OnSurface: Color = twSlate900

val SurfaceVariant: Color = twSlate100
val OnSurfaceVariant: Color = twSlate600

/* ---------- Outline ---------- */
val Outline: Color = twSlate400
val OutlineVariant: Color = twSlate200

/* ---------- Error ---------- */
val Error: Color = twRose
val OnError: Color = Color.White
val ErrorContainer: Color = twRose.copy(alpha = 0.15f)
val OnErrorContainer: Color = twRose

/* ---------- Inverse ---------- */
val InverseSurface: Color = twSlate900
val InverseOnSurface: Color = twBackgroundLight
val InversePrimary: Color = twPrimarySoft
val SurfaceTint: Color = Primary
val Scrim: Color = Color(0x66000000)

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
