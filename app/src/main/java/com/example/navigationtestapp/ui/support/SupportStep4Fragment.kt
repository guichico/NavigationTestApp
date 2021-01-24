package com.example.navigationtestapp.ui.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentSupportStep4Binding

class SupportStep4Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_support_step_4,
                container,
                false
            ) as FragmentSupportStep4Binding

        binding.nextStepButton.setOnClickListener {
            findNavController().navigate(SupportStep4FragmentDirections.actionStep4ToStep5())
        }

        return binding.root
    }
}