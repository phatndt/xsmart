package my.phatndt.xsmart

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
