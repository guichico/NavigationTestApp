package com.example.navigationtestapp.ui.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentMapBinding
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.viewmodel.map.MapViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment() {

    private val mapViewModel: MapViewModel by viewModel()
    private val args: MapFragmentArgs by navArgs()

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_map,
            container,
            false
        ) as FragmentMapBinding

        val place = args.place as? Place
        place?.let {
            mapViewModel.addMarker(it)
            arguments?.clear()
        }

        return binding.root
    }
}