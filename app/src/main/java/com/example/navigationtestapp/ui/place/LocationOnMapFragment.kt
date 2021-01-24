package com.example.navigationtestapp.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentLocationOnMapBinding
import com.example.navigationtestapp.ui.MainActivity
import com.example.navigationtestapp.viewmodel.place.LocationOnMapViewModel
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationOnMapFragment : Fragment() {

    private val locationOnMapViewModel: LocationOnMapViewModel by viewModel()

    private val args: LocationOnMapFragmentArgs by navArgs()

    lateinit var binding: FragmentLocationOnMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_location_on_map,
            container,
            false
        )

        val place = args.place
        binding.place = place

        (activity as MainActivity).supportActionBar?.title = place.name

        locationOnMapViewModel.moveToLocation(place)

        return binding.root
    }

    override fun onDestroy() {
        runBlocking {
            locationOnMapViewModel.removeMarker(binding.place!!)
        }

        super.onDestroy()
    }
}