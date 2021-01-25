package com.example.navigationtestapp.datastore

import android.content.Context
import com.example.navigationtestapp.repositiory.PersistentSettings
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class PersistentSettingsDataStore(context: Context) : PersistentSettings {

    companion object {
        private const val DEFAULT_ZOOM = 15F
    }

    private val settings = DataStoreBuilder(context, "settings")

    override suspend fun incrementSession() {
        coroutineScope {
            getSession()
                .map { it + 1 }
                .collect { settings.set("session", it) }
        }
    }

    override fun getSession(): Flow<Int> = settings.get("session", 0)

    override suspend fun saveLastLocation(latLng: LatLng) {
        coroutineScope {
            settings.set("last_latitude", latLng.latitude)
            settings.set("last_longitude", latLng.longitude)
        }
    }

    override suspend fun saveZoom(zoom: Float) = coroutineScope { settings.set("zoom", zoom) }

    override fun getLastLocation(): Flow<LatLng> {
        return settings.get("last_latitude", 0.0)
            .combine(
                settings.get("last_longitude", 0.0)
            ) { latitude, longitude ->
                LatLng(latitude, longitude)
            }
    }

    override fun getZoom(): Flow<Float> = settings.get("zoom", DEFAULT_ZOOM)
}