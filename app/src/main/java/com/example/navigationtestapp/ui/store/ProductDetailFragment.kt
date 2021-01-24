package com.example.navigationtestapp.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentProductDetailBinding
import com.example.navigationtestapp.viewmodel.store.ProductDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment : Fragment() {

    private val productDetailViewModel: ProductDetailViewModel by viewModel()

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_product_detail,
                container,
                false
            ) as FragmentProductDetailBinding

        binding.product = productDetailViewModel.getProductDetails(args.id)

        return binding.root
    }
}