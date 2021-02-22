package com.example.navigationtestapp.viewmodel.place

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.repositiory.PlacesRepository
import com.example.navigationtestapp.services.map.MapService

class PlaceDetailViewModel(
    private val placesRepository: PlacesRepository,
    private val mapService: MapService
) : ViewModel() {

    fun getPlaceDetails(placeId: String): Place? = placesRepository.getPlace(placeId)

    fun addMarker(place: Place) = mapService.addMarker(place)
}