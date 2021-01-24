package com.example.navigationtestapp.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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
            adapter = StoreAdapter(storeViewModel.getProducts())
        }

        return binding.root
    }

    class StoreAdapter(private val products: List<Product>) :
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
            holder.bind(product)
        }

        override fun getItemCount(): Int = products.size

    }

    class StoreViewHolder(private val binding: StoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            binding.root.setOnClickListener {
                binding.root.findNavController().navigate(
                    StoreFragmentDirections.actionStoreToProductDetail(product.id)
                )
            }
            binding.executePendingBindings()
        }
    }
}