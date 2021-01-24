package com.example.navigationtestapp.models

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Product(
    val id: String,
    val title: String,
    val size: String
) : Serializable