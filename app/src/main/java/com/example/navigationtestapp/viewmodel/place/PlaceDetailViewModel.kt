package com.example.navigationtestapp.viewmodel.place

import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.repositiory.PlacesRepository

class PlaceDetailViewModel(private val placesRepository: PlacesRepository) : ViewModel() {

    fun getPlaceDetails(placeId: String): Place? = placesRepository.getPlace(placeId)
}