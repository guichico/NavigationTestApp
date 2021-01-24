package com.example.navigationtestapp

import com.example.navigationtestapp.datastore.UserSettings
import com.example.navigationtestapp.repositiory.NotificationsRepository
import com.example.navigationtestapp.repositiory.PlacesRepository
import com.example.navigationtestapp.repositiory.ProductsRepository
import com.example.navigationtestapp.services.map.MapService
import com.example.navigationtestapp.viewmodel.MainActivityViewModel
import com.example.navigationtestapp.viewmodel.map.MapViewModel
import com.example.navigationtestapp.viewmodel.nearby.NearbyPlacesViewModel
import com.example.navigationtestapp.viewmodel.notifications.NotificationsViewModel
import com.example.navigationtestapp.viewmodel.place.LocationOnMapViewModel
import com.example.navigationtestapp.viewmodel.place.PlaceDetailViewModel
import com.example.navigationtestapp.viewmodel.store.ProductDetailViewModel
import com.example.navigationtestapp.viewmodel.store.StoreViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UserSettings(androidContext()) }

    // Repositories
    single { PlacesRepository(androidContext()) }
    single { NotificationsRepository(androidContext()) }
    single { ProductsRepository(androidContext()) }

    // ViewModel´s
    viewModel { MainActivityViewModel(get()) }
    viewModel { MapViewModel(get()) }
    viewModel { NearbyPlacesViewModel(get()) }
    viewModel { NotificationsViewModel(get()) }
    viewModel { PlaceDetailViewModel(get()) }
    viewModel { LocationOnMapViewModel(get()) }
    viewModel { ProductDetailViewModel(get()) }
    viewModel { StoreViewModel(get()) }

    // Services
    single { MapService(androidContext(), get()) }
}