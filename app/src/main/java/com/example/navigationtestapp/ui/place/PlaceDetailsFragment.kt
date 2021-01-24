package com.example.navigationtestapp.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentPlaceDetailBinding
import com.example.navigationtestapp.ui.MainActivity
import com.example.navigationtestapp.viewmodel.place.PlaceDetailViewModel
import com.google.android.gms.maps.MapFragment
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

        binding.place = placeDetailViewModel.getPlaceDetails(args.place.id)

        binding.goToButton.setOnClickListener {
        }

        binding.writeAReviewButton.setOnClickListener {
            findNavController().navigate(
                PlaceDetailsFragmentDirections.actionPlaceDetailsToWriteReview()
            )
        }

        return binding.root
    }
}