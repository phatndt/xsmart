package my.xsmart.share.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

object Spacing {
    /**
     * 2dp
     */
    val mSmall = 2.dp

    /**
     * 4dp
     */
    val small = 4.dp

    /**
     * 8dp
     */
    val medium = 8.dp

    /**
     * 16dp
     */
    val large = 16.dp

    /**
     * 24dp
     */
    val xLarge = 24.dp

    /**
     * 32dp
     */
    val xxLarge = 32.dp

    /**
     * 40dp
     */
    val xxxLarge = 40.dp
}

class AppSpacing {

    // ───────────────
    // XS – Micro spacing (inline / tight UI)
    // ───────────────

    val xxs = 4.dp
    // Tailwind: space-y-1, gap-1, p-1, m-1
    // Use: icon ↔ text, chip padding, dense list

    val xs = 6.dp
    // Tailwind: space-y-1.5, gap-1.5, px-1.5, mb-1.5
    // Use: label ↔ value, helper text, compact form rows

    val sm = 8.dp
    // Tailwind: space-y-2, gap-2, p-2, m-2
    // Use: small padding, list item spacing, compact cards


    // ───────────────
    // Content spacing (standard UI rhythm)
    // ───────────────

    val smPlus = 10.dp
    // Tailwind: space-y-2.5, gap-2.5, mb-2.5
    // Use: text blocks, form rows, subtle separation

    val md = 12.dp
    // Tailwind: space-y-3, gap-3, p-3, mb-3
    // Use: card content spacing, form sections

    val lg = 16.dp
    // Tailwind: space-y-4, gap-4, p-4, m-4
    // Use: card padding, list sections, default content spacing

    val xl = 20.dp
    // Tailwind: space-y-5, gap-5, p-5, mb-5
    // Use: major content blocks, card ↔ card spacing

    val xxl = 24.dp
    // Tailwind: space-y-6, gap-6, p-6, mb-6
    // Use: large cards, modal content, screen padding (compact)


    // ───────────────
    // Section spacing (layout / hierarchy)
    // ───────────────

    val section = 32.dp
    // Tailwind: space-y-8, gap-8, mb-8, pb-8
    // Use: section ↔ section, screen content separation


    // ───────────────
    // Screen / system spacing
    // ───────────────

    val bottomBarSpace = 128.dp
    // Tailwind: pb-32
    // Use: fixed bottom CTA, avoid content overlap with bottom bar
}

internal val LocalSpacing = compositionLocalOf { AppSpacing() }
