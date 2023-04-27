package com.example.restapp

import com.example.restapp.network.SinglePhoto

data class JsonObjectFewMemes(
    val count: Int,
    val memes: List<SinglePhoto>
)
