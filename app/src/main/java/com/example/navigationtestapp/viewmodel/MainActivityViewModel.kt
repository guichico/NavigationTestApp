package com.example.navigationtestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtestapp.repositiory.PersistentSettings
import com.example.navigationtestapp.services.map.MapService
import com.google.android.gms.maps.GoogleMap
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val mapService: MapService,
    private val persistentSettings: PersistentSettings
) : ViewModel() {

    fun startMap(map: GoogleMap) =
        mapService.startMap(map)

    suspend fun saveMapLocationAndZoom() = mapService.saveMapLocationAndZoom()

    fun moveToLastLocation() =
        viewModelScope.launch {
            persistentSettings.getLastLocation()
                .collect {
                    if (it.latitude == 0.0 && it.longitude == 0.0) {
                        mapService.moveToMyLocation()
                    } else {
                        mapService.moveToLocation(it)
                    }
                }
        }
}