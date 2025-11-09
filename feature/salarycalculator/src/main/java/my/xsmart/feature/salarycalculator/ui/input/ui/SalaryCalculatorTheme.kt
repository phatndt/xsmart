package my.xsmart.feature.salarycalculator.ui.input.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
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

/* ---------- Colors (from your CSS tokens) ---------- */
/* NOTE: Compose uses sRGB; OKLCH values are approximated to sRGB where needed. */

/* Light */
private val L_Background         = Color(0xFFFFFFFF) // --background
private val L_Foreground         = Color(0xFF0A0A0A) // ≈ oklch(0.145 0 0)
private val L_Card               = Color(0xFFFFFFFF)
private val L_Primary            = Color(0xFF030213) // --primary
private val L_OnPrimary          = Color(0xFFFFFFFF) // --primary-foreground
private val L_Secondary          = Color(0xFFF2F2F5) // ≈ oklch(0.95 0.0058 264.53)
private val L_OnSecondary        = Color(0xFF030213)
private val L_Muted              = Color(0xFFECECF0)
private val L_OnMuted            = Color(0xFF717182)
private val L_Accent             = Color(0xFFE9EBEF)
private val L_OnAccent           = Color(0xFF030213)
private val L_Error              = Color(0xFFD4183D) // --destructive
private val L_OnError            = Color(0xFFFFFFFF) // --destructive-foreground
private val L_Outline            = Color(0x1A000000) // rgba(0,0,0,0.1)
private val L_SurfaceVariant     = L_Muted
private val L_OnSurfaceVariant   = L_OnMuted

/* Dark */
private val D_Background         = Color(0xFF0E0E10) // ≈ oklch(0.145 0 0)
private val D_Foreground         = Color(0xFFFDFDFD) // ≈ oklch(0.985 0 0)
private val D_Card               = D_Background
private val D_Primary            = Color(0xFFFDFDFD) // ≈ oklch(0.985 0 0)
private val D_OnPrimary          = Color(0xFF353535) // ≈ oklch(0.205 0 0)
private val D_Secondary          = Color(0xFF2B2B2E) // ≈ oklch(0.269 0 0)
private val D_OnSecondary        = Color(0xFFFDFDFD)
private val D_Muted              = Color(0xFF2B2B2E)
private val D_OnMuted            = Color(0xFFB5B5B9) // ≈ oklch(0.708 0 0)
private val D_Accent             = Color(0xFF2B2B2E)
private val D_OnAccent           = Color(0xFFFDFDFD)
private val D_Error              = Color(0xFFD4183D) // close to your dark destructive
private val D_OnError            = Color(0xFFFFEDEB) // ≈ oklch(0.637 0.237 25.331)
private val D_Outline            = Color(0xFF2B2B2E)
private val D_SurfaceVariant     = D_Muted
private val D_OnSurfaceVariant   = D_OnMuted

/* Extras (non-M3 tokens) */
@Immutable
data class AppExtraColors(
    val input: Color,
    val inputBackground: Color,
    val switchBackground: Color,
    val ring: Color,
    val chart1: Color,
    val chart2: Color,
    val chart3: Color,
    val chart4: Color,
    val chart5: Color,
    val sidebar: Color,
    val onSidebar: Color,
    val sidebarPrimary: Color,
    val onSidebarPrimary: Color,
    val sidebarAccent: Color,
    val onSidebarAccent: Color,
    val sidebarBorder: Color,
    val sidebarRing: Color,
)

private val LocalExtraColors = staticCompositionLocalOf<AppExtraColors> {
    error("No AppExtraColors provided")
}

/* Light extras mapped from CSS */
private val LightExtras = AppExtraColors(
    input            = Color.Transparent,                  // --input
    inputBackground  = Color(0xFFF3F3F5),                  // --input-background
    switchBackground = Color(0xFFCBCED4),                  // --switch-background
    ring             = Color(0xFFB5B5B5),                  // ≈ oklch(0.708 0 0)
    chart1           = Color(0xFFDF7C3A),                  // ≈ oklch(0.646 0.222 41.116)
    chart2           = Color(0xFF4FAAD6),                  // ≈ oklch(0.6 0.118 184.704)
    chart3           = Color(0xFF5770C8),                  // ≈ oklch(0.398 0.07 227.392)
    chart4           = Color(0xFFB7D65C),                  // ≈ oklch(0.828 0.189 84.429)
    chart5           = Color(0xFFA9D24F),                  // ≈ oklch(0.769 0.188 70.08)
    sidebar          = Color(0xFFFAFAFA),                  // ≈ oklch(0.985 0 0)
    onSidebar        = L_Foreground,
    sidebarPrimary   = L_Primary,
    onSidebarPrimary = Color(0xFFFDFDFD),
    sidebarAccent    = Color(0xFFF5F5F5),                  // ≈ oklch(0.97 0 0)
    onSidebarAccent  = Color(0xFF363636),                  // ≈ oklch(0.205 0 0)
    sidebarBorder    = Color(0xFFEAEAEA),                  // ≈ oklch(0.922 0 0)
    sidebarRing      = Color(0xFFB5B5B5),                  // ≈ oklch(0.708 0 0)
)

/* Dark extras mapped from CSS */
private val DarkExtras = AppExtraColors(
    input            = Color(0xFF2B2B2E),
    inputBackground  = Color(0xFF2B2B2E),
    switchBackground = Color(0xFF71747A),
    ring             = Color(0xFF6F6F73),                  // ≈ oklch(0.439 0 0)
    chart1           = Color(0xFF6E89FF),                  // ≈ oklch(0.488 0.243 264.376)
    chart2           = Color(0xFF7DE0C7),                  // ≈ oklch(0.696 0.17 162.48)
    chart3           = Color(0xFFA9D24F),                  // ≈ oklch(0.769 0.188 70.08)
    chart4           = Color(0xFFB18BFF),                  // ≈ oklch(0.627 0.265 303.9)
    chart5           = Color(0xFFF2A66A),                  // ≈ oklch(0.645 0.246 16.439)
    sidebar          = Color(0xFF343437),                  // ≈ oklch(0.205 0 0)
    onSidebar        = D_Foreground,
    sidebarPrimary   = Color(0xFF6E89FF),                  // ≈ oklch(0.488 0.243 264.376)
    onSidebarPrimary = Color(0xFFFDFDFD),
    sidebarAccent    = Color(0xFF2B2B2E),
    onSidebarAccent  = Color(0xFFFDFDFD),
    sidebarBorder    = Color(0xFF2B2B2E),
    sidebarRing      = Color(0xFF6F6F73),
)

/* ---------- Material3 ColorSchemes ---------- */

private val LightColors: ColorScheme = lightColorScheme(
    primary = L_Primary,
    onPrimary = L_OnPrimary,
    background = L_Background,
    onBackground = L_Foreground,
    surface = L_Card,
    onSurface = L_Foreground,
    surfaceVariant = L_SurfaceVariant,
    onSurfaceVariant = L_OnSurfaceVariant,
    secondary = L_Secondary,
    onSecondary = L_OnSecondary,
    tertiary = L_Accent,
    onTertiary = L_OnAccent,
    error = L_Error,
    onError = L_OnError,
    outline = L_Outline
)

private val DarkColors: ColorScheme = darkColorScheme(
    primary = D_Primary,
    onPrimary = D_OnPrimary,
    background = D_Background,
    onBackground = D_Foreground,
    surface = D_Card,
    onSurface = D_Foreground,
    surfaceVariant = D_SurfaceVariant,
    onSurfaceVariant = D_OnSurfaceVariant,
    secondary = D_Secondary,
    onSecondary = D_OnSecondary,
    tertiary = D_Accent,
    onTertiary = D_OnAccent,
    error = D_Error,
    onError = D_OnError,
    outline = D_Outline
)

/* ---------- Shapes (from --radius) ----------
   --radius = 0.625rem ≈ 10px → ~10.dp visually in Compose.
   radius-sm: 6dp, md: 8dp, lg: 10dp, xl: 14dp
*/
private val ShapesLightDark = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(6.dp),
    small      = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    medium     = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
    large      = androidx.compose.foundation.shape.RoundedCornerShape(14.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
)

/* ---------- Typography ----------
   Base font-size: 16px → 16sp. Medium = 500, Normal = 400.
*/
private val AppTypography = androidx.compose.material3.Typography(
    displayLarge  = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium, lineHeight = 36.sp), // h1
    headlineLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, lineHeight = 30.sp), // h2
    headlineMedium= TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, lineHeight = 27.sp), // h3
    titleLarge    = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, lineHeight = 24.sp), // h4
    bodyLarge     = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 24.sp), // p
    labelLarge    = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, lineHeight = 24.sp), // label/button
    bodyMedium    = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 21.sp),
    labelMedium   = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 21.sp)
)

/* ---------- Public Theme API ---------- */

object AppThemeTokens {
    val colors: AppExtraColors
        @Composable get() = LocalExtraColors.current
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    val extras = if (darkTheme) DarkExtras else LightExtras

    androidx.compose.runtime.CompositionLocalProvider(
        LocalExtraColors provides extras
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = AppTypography,
            shapes = ShapesLightDark,
            content = content
        )
    }
}

/* ---------- Helpers for one-off usages ---------- */

// Example: focused ring color (use in borders/shadows where Material doesn't provide one)
val MaterialTheme.ringColor: Color
    @Composable get() = AppThemeTokens.colors.ring

// Example: input backgrounds
val MaterialTheme.inputBackground: Color
    @Composable get() = AppThemeTokens.colors.inputBackground

// Example: sidebar palette
object SidebarColors {
    val container: Color @Composable get() = AppThemeTokens.colors.sidebar
    val onContainer: Color @Composable get() = AppThemeTokens.colors.onSidebar
    val primary: Color @Composable get() = AppThemeTokens.colors.sidebarPrimary
    val onPrimary: Color @Composable get() = AppThemeTokens.colors.onSidebarPrimary
    val accent: Color @Composable get() = AppThemeTokens.colors.sidebarAccent
    val onAccent: Color @Composable get() = AppThemeTokens.colors.onSidebarAccent
    val border: Color @Composable get() = AppThemeTokens.colors.sidebarBorder
    val ring: Color @Composable get() = AppThemeTokens.colors.sidebarRing
}

// Example: chart palette
object ChartColors {
    val c1: Color @Composable get() = AppThemeTokens.colors.chart1
    val c2: Color @Composable get() = AppThemeTokens.colors.chart2
    val c3: Color @Composable get() = AppThemeTokens.colors.chart3
    val c4: Color @Composable get() = AppThemeTokens.colors.chart4
    val c5: Color @Composable get() = AppThemeTokens.colors.chart5
}
