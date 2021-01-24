package com.example.navigationtestapp.viewmodel.nearby

import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.repositiory.PlacesRepository

class NearbyPlacesViewModel(private val placesRepository: PlacesRepository) : ViewModel() {

    fun getPlaces(): List<Place> = placesRepository.getPlaces()
}