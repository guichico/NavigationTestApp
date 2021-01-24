package com.example.navigationtestapp.viewmodel.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.repositiory.ProductsRepository
import com.example.navigationtestapp.viewmodel.store.ProductDetailViewModel.InstallStatus.*
import java.util.*
import kotlin.concurrent.schedule

class ProductDetailViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    enum class InstallStatus { NONE, INSTALLING, INSTALLED }

    lateinit var installTask: TimerTask
    val installStatus = MutableLiveData<InstallStatus>().apply { value = NONE }

    fun getProductDetails(productId: String) = productsRepository.getProduct(productId)

    fun startDownload() {
        installStatus.postValue(INSTALLING)

        installTask = Timer().schedule(5000) {
            installStatus.postValue(INSTALLED)
        }
    }

    fun cancelDownload() {
        installTask.cancel()
        installStatus.postValue(NONE)
    }
}