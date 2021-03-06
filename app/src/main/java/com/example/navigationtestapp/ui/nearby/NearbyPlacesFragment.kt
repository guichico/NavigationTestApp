package com.example.navigationtestapp.ui.nearby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentNearbyPlacesBinding
import com.example.navigationtestapp.databinding.NearbyPlacesItemBinding
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.viewmodel.nearby.NearbyPlacesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NearbyPlacesFragment : Fragment() {

    private val nearbyPlacesViewModel: NearbyPlacesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_nearby_places,
                container,
                false
            ) as FragmentNearbyPlacesBinding

        binding.nearbyPlacesList.apply {
            setHasFixedSize(true)
            adapter = NearbyAdapter(
                nearbyPlacesViewModel.getPlaces(),
                NearbyListener { goToPlaceDetails(it) })
        }

        return binding.root
    }

    fun goToPlaceDetails(place: Place) {
        findNavController()
            .navigate(NearbyPlacesFragmentDirections.actionNearbyToPlaceDetail(place))
    }

    class NearbyListener(val clickListener: (place: Place) -> Unit) {
        fun onClick(place: Place) = clickListener(place)
    }

    class NearbyAdapter(
        private val places: List<Place>,
        private val clickListener: NearbyListener
    ) :
        RecyclerView.Adapter<NearbyPlaceViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyPlaceViewHolder {
            return NearbyPlaceViewHolder(
                NearbyPlacesItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: NearbyPlaceViewHolder, position: Int) {
            val place = places[position]
            holder.bind(place, clickListener)
        }

        override fun getItemCount(): Int = places.size

    }

    class NearbyPlaceViewHolder(private val binding: NearbyPlacesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place, clickListener: NearbyListener) {
            binding.place = place
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}