package com.example.paging3withmvvm.data

import com.squareup.moshi.Json

data class Pics(
    val author: String,
    val download_url: String,
    val height: Int=50,
    val id: String,
    val url: String,
    val width: Int=50
)

