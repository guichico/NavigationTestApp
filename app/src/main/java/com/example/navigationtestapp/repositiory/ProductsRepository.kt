package com.example.navigationtestapp.repositiory

import android.content.Context
import com.example.navigationtestapp.models.Product
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ProductsRepository(val context: Context) {

    fun getProducts(): List<Product> {
        val jsonAdapter: JsonAdapter<List<Product>> = Moshi.Builder()
            .build()
            .adapter(
                Types.newParameterizedType(
                    MutableList::class.java,
                    Product::class.java
                )
            )
        return jsonAdapter.fromJson(
            context.assets.open("products.json")
                .bufferedReader()
                .use { it.readText() }
        ) as List<Product>
    }

    fun getProduct(productId: String) = getProducts().firstOrNull { it.id == productId }
}