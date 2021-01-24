package com.example.navigationtestapp.repositiory

import android.content.Context
import com.example.navigationtestapp.models.Place
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class PlacesRepository(val context: Context) {

    fun getPlaces(): List<Place> {
        val jsonAdapter: JsonAdapter<List<Place>> = Moshi.Builder()
            .build()
            .adapter(
                Types.newParameterizedType(
                    MutableList::class.java,
                    Place::class.java
                )
            )
        return jsonAdapter.fromJson(
            context.assets.open("nearbyPlaces.json")
                .bufferedReader()
                .use { it.readText() }
        ) as List<Place>
    }

    fun getPlace(placeId: String): Place? = getPlaces().firstOrNull { it.id == placeId }
}