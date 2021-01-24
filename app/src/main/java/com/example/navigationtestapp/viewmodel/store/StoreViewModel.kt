package com.example.navigationtestapp.viewmodel.store

import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.repositiory.ProductsRepository

class StoreViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    fun getProducts() = productsRepository.getProducts()
}