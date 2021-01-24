package com.example.navigationtestapp.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentPlaceDetailBinding
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.viewmodel.place.PlaceDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaceDetailsFragment : Fragment() {

    private val placeDetailViewModel: PlaceDetailViewModel by viewModel()

    private val args: PlaceDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_place_detail,
                container,
                false
            ) as FragmentPlaceDetailBinding

        val place = placeDetailViewModel.getPlaceDetails(args.place.id)
        binding.place = place

        binding.seeLocationOnMapButton.setOnClickListener { seeLocationOnMapClicked(place!!) }
        binding.addMarkerButton.setOnClickListener { addMarkerClicked(place!!) }
        binding.writeAReviewButton.setOnClickListener { writeAReviewClicked() }

        return binding.root
    }

    private fun seeLocationOnMapClicked(place: Place) {
        findNavController().navigate(
            PlaceDetailsFragmentDirections.actionPlaceDetailsToLocationOnMap(place)
        )
    }

    private fun addMarkerClicked(place: Place) {
        placeDetailViewModel.addMarker(place)

        findNavController().navigate(
            PlaceDetailsFragmentDirections.actionPlaceDetailsToMap()
        )
    }

    private fun writeAReviewClicked() {
        findNavController().navigate(
            PlaceDetailsFragmentDirections.actionPlaceDetailsToWriteReview()
        )
    }
}