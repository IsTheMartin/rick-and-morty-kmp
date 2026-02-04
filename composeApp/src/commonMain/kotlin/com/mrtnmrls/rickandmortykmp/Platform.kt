package com.mrtnmrls.rickandmortykmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform