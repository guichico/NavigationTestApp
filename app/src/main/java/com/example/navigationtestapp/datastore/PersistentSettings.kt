package com.example.navigationtestapp.datastore

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class PersistentSettings(val context: Context) {

    companion object {
        private const val DEFAULT_ZOOM = 15F
    }

    private val settings = DataStoreBuilder(context, "settings")

    suspend fun saveLastLocation(latLng: LatLng) {
        coroutineScope {
            settings.set("last_latitude", latLng.latitude)
            settings.set("last_longitude", latLng.longitude)
        }
    }

    suspend fun saveZoom(zoom: Float) = coroutineScope { settings.set("zoom", zoom) }

    fun getLastLocation(): Flow<LatLng> {
        return settings.get("last_latitude", 0.0)
            .combine(
                settings.get("last_longitude", 0.0)
            ) { latitude, longitude ->
                LatLng(latitude, longitude)
            }
    }

    fun getZoom(): Flow<Float> = settings.get("zoom", DEFAULT_ZOOM)
}