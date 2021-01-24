package com.example.navigationtestapp.ui.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentSupportStep1Binding

class SupportStep1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_support_step_1,
                container,
                false
            ) as FragmentSupportStep1Binding

        binding.nextStepButton.setOnClickListener {
            findNavController().navigate(SupportStep1FragmentDirections.actionStep1ToStep2())
        }

        return binding.root
    }
}