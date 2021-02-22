package com.example.navigationtestapp.ui.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentSupportStep3Binding

class SupportStep3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_support_step_3,
                container,
                false
            ) as FragmentSupportStep3Binding

        binding.fragment = this

        return binding.root
    }

    fun goToNextStep() =
        findNavController().navigate(SupportStep3FragmentDirections.actionStep3ToStep4())
}