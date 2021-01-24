package com.example.navigationtestapp.ui.notifications

import com.squareup.moshi.JsonClass
import java.io.Serializable

enum class Type { PLACE, PRODUCT }

@JsonClass(generateAdapter = true)
data class Notification(
    val title: String,
    val subTitle: String,
    val type: Type,
    val referenceId: String
) : Serializable