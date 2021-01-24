package com.example.navigationtestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtestapp.services.map.MapService
import com.google.android.gms.maps.GoogleMap
import kotlinx.coroutines.launch

class MainActivityViewModel(private val mapService: MapService) : ViewModel() {

    fun startMap(map: GoogleMap) =
        mapService.startMap(map)

    suspend fun saveMapLocationAndZoom() = mapService.saveMapLocationAndZoom()

    fun moveToLastLocation() =
        viewModelScope.launch { mapService.moveToLastLocation() }

}