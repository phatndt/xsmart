package my.phatndt.xsmart.share

import android.icu.number.NumberFormatter
import android.os.Build
import java.text.NumberFormat
import java.util.Locale

actual fun Double?.toDisplayAmount(): String {
    // Return an empty string if the value is null
    this ?: return ""

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // Use NumberFormatter for API level 30 (Android R) and above
        NumberFormatter.withLocale(Locale.US).format(this).toString()
    } else {
        // Use NumberFormat for devices with API level below 30
        NumberFormat.getNumberInstance(Locale.US).format(this)
    }
}
