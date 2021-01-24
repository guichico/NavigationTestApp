package com.example.navigationtestapp.ui.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentSupportBinding

class SupportFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_support,
                container,
                false
            ) as FragmentSupportBinding

        return binding.root
    }
}