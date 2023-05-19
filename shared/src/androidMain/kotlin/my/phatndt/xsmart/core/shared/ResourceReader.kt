package my.phatndt.xsmart.core.shared

import java.io.File
import java.io.InputStreamReader

actual class ResourceReader {
    actual fun readResource(name: String): String {
        return try {
            javaClass.classLoader?.getResource(name)?.readText()!!
        } catch (e: Exception) {
            e.printStackTrace()
            "[]"
        }
    }
}