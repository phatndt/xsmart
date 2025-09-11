package my.phatndt.xsmart.utils

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object AndroidUtils {
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    fun isAtLeastR() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
}
