package com.example.navigationtestapp.viewmodel.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.services.map.MapService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class MapViewModel(private val mapService: MapService) : ViewModel() {

    fun moveToLocation(latLng: LatLng) =
        mapService.moveToLocation(latLng, MapService.DEFAULT_ZOOM)

    fun addMarker(place: Place) {
        viewModelScope.launch {
            mapService.addMarker(place)
        }
    }
}