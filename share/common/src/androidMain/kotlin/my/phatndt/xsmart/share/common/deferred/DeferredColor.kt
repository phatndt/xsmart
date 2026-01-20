package my.phatndt.xsmart.share.common.deferred

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.graphics.Color as ComposeColor

sealed interface DeferredColor {
    fun resolve(context: Context): ComposeColor

    class Constant(
        private val value: Int,
    ) : DeferredColor {
        override fun resolve(context: Context): ComposeColor {
            return ComposeColor(value)
        }
    }

    class Color(
        val color: ComposeColor,
    ) : DeferredColor {
        override fun resolve(context: Context): ComposeColor {
            return color
        }
    }
}


