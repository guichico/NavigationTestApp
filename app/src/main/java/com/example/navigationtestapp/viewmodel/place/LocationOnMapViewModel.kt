package com.example.navigationtestapp.viewmodel.place

import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.services.map.MapService

class LocationOnMapViewModel(private val mapService: MapService) : ViewModel() {

    fun moveToLocation(place: Place) = mapService.addMarker(place)

    fun removeMarker(place: Place) = mapService.removeMarker(place)
}