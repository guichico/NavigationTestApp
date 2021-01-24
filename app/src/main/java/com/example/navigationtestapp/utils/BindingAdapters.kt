package com.example.navigationtestapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imgUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) imageView.load(url)
    }
}

