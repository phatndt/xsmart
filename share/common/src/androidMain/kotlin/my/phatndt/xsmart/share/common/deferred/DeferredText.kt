package my.phatndt.xsmart.share.common.deferred

import android.content.Context
import androidx.annotation.StringRes

sealed interface DeferredText {
    fun resolve(context: Context): String

    class Constant(
        private val text: String,
    ): DeferredText {
        override fun resolve(context: Context): String {
            return text
        }
    }

    data class Resource(
        @param:StringRes private val resId: Int,
    ): DeferredText {
        override fun resolve(context: Context): String {
            return context.getString(resId)
        }
    }
}

