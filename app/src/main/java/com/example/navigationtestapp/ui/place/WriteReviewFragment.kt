package com.example.navigationtestapp.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentWriteReviewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WriteReviewFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_write_review,
                container,
                false
            ) as FragmentWriteReviewBinding

        binding.sendReviewButton.setOnClickListener {
            findNavController().navigate(
                WriteReviewFragmentDirections.actionWriteReviewToReviewSent()
            )
        }

        return binding.root
    }
}