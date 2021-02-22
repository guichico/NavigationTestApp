package com.example.navigationtestapp.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentStoreBinding
import com.example.navigationtestapp.databinding.StoreItemBinding
import com.example.navigationtestapp.models.Product
import com.example.navigationtestapp.viewmodel.store.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : Fragment() {

    private val storeViewModel: StoreViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_store,
                container,
                false
            ) as FragmentStoreBinding

        binding.storeList.apply {
            setHasFixedSize(true)
            adapter = StoreAdapter(
                storeViewModel.getProducts(),
                ProductListener { goToProductDetails(it) })
        }

        return binding.root
    }

    private fun goToProductDetails(product: Product) {
        findNavController().navigate(
            StoreFragmentDirections.actionStoreToProductDetail(product.id)
        )
    }

    class ProductListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }

    class StoreAdapter(
        private val products: List<Product>,
        private val clickListener: ProductListener
    ) :
        RecyclerView.Adapter<StoreViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
            return StoreViewHolder(
                StoreItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
            val product = products[position]
            holder.bind(product, clickListener)
        }

        override fun getItemCount(): Int = products.size

    }

    class StoreViewHolder(private val binding: StoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, clickListener: ProductListener) {
            binding.product = product
            binding.clickListener = clickListener

            binding.executePendingBindings()
        }
    }
}