package com.example.navigationtestapp.services.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.example.navigationtestapp.datastore.UserSettings
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.models.toLatLng
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect

class MapService(
    private val context: Context,
    private val userSettings: UserSettings
) {

    companion object {
        const val DEFAULT_ZOOM = 15F
    }

    private lateinit var locationManager: LocationManager
    lateinit var gMap: GoogleMap

    private val markers = mutableMapOf<String, MarkerOptions>()

    @SuppressLint("MissingPermission")
    fun startMap(map: GoogleMap) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        gMap = map
        gMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        gMap.isMyLocationEnabled = true
        gMap.setOnMyLocationButtonClickListener {
            moveToMyLocation()
            true
        }
    }

    suspend fun saveMapLocationAndZoom() {
        coroutineScope {
            userSettings.saveLastLocation(gMap.cameraPosition.target)
            userSettings.saveZoom(gMap.cameraPosition.zoom)
        }
    }

    @SuppressLint("MissingPermission")
    fun moveToMyLocation() {
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
            val myLocation = LatLng(it.latitude, it.longitude)
            moveToLocation(myLocation, DEFAULT_ZOOM)
        }
    }

    suspend fun moveToLocation(latLng: LatLng) {
        userSettings.getZoom()
            .collect { zoom ->
                moveToLocation(latLng, zoom)
            }
    }

    fun moveToLocation(latLng: LatLng, zoom: Float) =
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))

    suspend fun moveToLastLocation() {
        userSettings.getLastLocation()
            .collect {
                moveToLocation(it)
            }
    }

    fun addMarker(place: Place) {
        val position = place.latLong.toLatLng()
        val marker = MarkerOptions().position(position).title(place.name)

        markers[place.name] = marker

        gMap.addMarker(marker)
        moveToLocation(position, DEFAULT_ZOOM)
    }

    fun removeMarker(place: Place) {
        markers.remove(place.name)

        gMap.clear()
        markers.forEach {
            gMap.addMarker(it.value)
        }
    }
}