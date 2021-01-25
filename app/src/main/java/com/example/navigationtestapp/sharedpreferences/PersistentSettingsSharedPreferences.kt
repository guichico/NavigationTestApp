package com.example.navigationtestapp.sharedpreferences

import android.content.Context
import com.example.navigationtestapp.repositiory.PersistentSettings
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PersistentSettingsSharedPreferences(context: Context) : PersistentSettings {

    companion object {
        private const val DEFAULT_ZOOM = 15F
    }

    private val settings = SharedPreferencesBuilder(context, "settings")

    override suspend fun incrementSession() {
        val session = settings.get("session", 0)
        settings.set("session", session + 1)
    }

    override fun getSession(): Flow<Int> {
        return flow {
            emit(settings.get("session", 0))
        }
    }

    override suspend fun saveLastLocation(latLng: LatLng) {
        settings.set("last_latitude", latLng.latitude)
        settings.set("last_longitude", latLng.longitude)
    }

    override suspend fun saveZoom(zoom: Float) = settings.set("zoom", zoom)

    override fun getLastLocation(): Flow<LatLng> {
        return flow {
            val latitude = settings.get("last_latitude", 0.0)
            val longitude = settings.get("last_longitude", 0.0)

            emit(LatLng(latitude, longitude))
        }
    }

    override fun getZoom(): Flow<Float> {
        return flow {
            emit(settings.get("zoom", DEFAULT_ZOOM))
        }
    }
}