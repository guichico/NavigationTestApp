package com.example.navigationtestapp.repositiory

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface PersistentSettings {

    suspend fun incrementSession()

    fun getSession() : Flow<Int>

    suspend fun saveLastLocation(latLng: LatLng)

    suspend fun saveZoom(zoom: Float)

    fun getLastLocation(): Flow<LatLng>

    fun getZoom(): Flow<Float>

}