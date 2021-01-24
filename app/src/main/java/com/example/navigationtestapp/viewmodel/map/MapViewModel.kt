package com.example.navigationtestapp.viewmodel.map

import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.services.map.MapService

class MapViewModel(private val mapService: MapService) : ViewModel() {

    fun addMarker(place: Place) = mapService.addMarker(place)
}