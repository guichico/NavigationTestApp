package com.example.navigationtestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.services.map.MapService
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class MainActivityViewModel(private val mapService: MapService) : ViewModel() {

    fun startMap(map: GoogleMap) =
        mapService.startMap(map)

    suspend fun saveMapLocationAndZoom() = mapService.saveMapLocationAndZoom()

    fun moveToMyLocation() = mapService.moveToMyLocation()

    fun moveToLocation(latLng: LatLng) =
        viewModelScope.launch { mapService.moveToLocation(latLng) }

    fun moveToLocation(latLng: LatLng, zoom: Float) =
        mapService.moveToLocation(latLng, zoom)

    fun moveToLastLocation() =
        viewModelScope.launch { mapService.moveToLastLocation() }

    fun addMarker(place: Place) =
        viewModelScope.launch { mapService.addMarker(place) }
}