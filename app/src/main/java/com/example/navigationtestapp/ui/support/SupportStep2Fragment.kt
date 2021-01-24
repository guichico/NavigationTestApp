package com.example.navigationtestapp.ui.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentSupportStep2Binding

class SupportStep2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_support_step_2,
                container,
                false
            ) as FragmentSupportStep2Binding

        binding.nextStepButton.setOnClickListener {
            findNavController().navigate(SupportStep2FragmentDirections.actionStep2ToStep3())
        }

        return binding.root
    }
}