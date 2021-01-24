package com.example.navigationtestapp.models

import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Place(
    val id: String,
    var name: String = "",
    var distance: String = "",
    var type: String = "",
    var imageUrl: String = "",
    var latLong: List<Double> = emptyList()
) : Serializable

fun List<Double>.toLatLng(): LatLng = LatLng(get(0), get(1))