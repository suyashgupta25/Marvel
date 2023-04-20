package com.marvel.buildsrc

object Configs {

    //Server URL
    const val API_BASE_URL = "\"https://gateway.marvel.com/\""

    //Marvel API keys
    const val MARVEL_PUBLIC_KEY = "\"cfdff0f04c624fbf99e981c55c5b433c\""
    const val MARVEL_PRIVATE_KEY = "\"\""

    //Database
    const val DATABASE_NAME = "\"marvel-db\""
    const val DATABASE_VERSION = "1"
    const val DATABASE_EXPORT_SCHEMA = "false"

    //Image library cache configs
    const val IMAGE_DISK_CACHE_NAME = "\"marvel-image-cache\""
    const val IMAGE_DISK_CACHE_PERCENT = "0.02" // 2 percent
    const val IMAGE_MEMORY_CACHE_PERCENT = "0.25" // 25 percent
}