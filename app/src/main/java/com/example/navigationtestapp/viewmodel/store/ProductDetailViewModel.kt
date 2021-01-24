package com.example.navigationtestapp.viewmodel.store

import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.repositiory.ProductsRepository

class ProductDetailViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    fun getProductDetails(productId: String) = productsRepository.getProduct(productId)
}