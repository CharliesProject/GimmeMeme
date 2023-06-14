package com.example.restapp.network

import com.squareup.moshi.Json

data class SinglePhoto(
    val postLink: String,
    val subreddit: String,
    val title: String,
    @Json(name = "url") val imgSrcUrl: String,
    val nsfw: Boolean,
    val spoiler: Boolean,
    val author: String,
    val ups: String,
    val preview: List<String>
)
