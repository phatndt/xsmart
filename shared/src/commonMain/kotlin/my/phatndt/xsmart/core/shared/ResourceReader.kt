package my.phatndt.xsmart.core.shared

expect class ResourceReader {
    fun readResource(name: String): String
}