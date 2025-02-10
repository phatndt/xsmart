package my.phatndt.xsmart.android.core.utils

import android.annotation.SuppressLint
import java.sql.Date
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun getDateTimeFromString(s: String): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val netDate = Date(s.toLong())
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}

@SuppressLint("SimpleDateFormat")
fun getDateFromString(s: String): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(s.toLong())
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}
