package com.example.xsmart

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform