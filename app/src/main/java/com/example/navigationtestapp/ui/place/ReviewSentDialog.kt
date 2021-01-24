package com.example.navigationtestapp.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.DialogReviewSentBinding
import java.util.*
import kotlin.concurrent.schedule

class ReviewSentDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_review_sent,
                container,
                false
            ) as DialogReviewSentBinding

        isCancelable = false

        Timer().schedule(3000) {
            dismiss()
        }

        return binding.root
    }
}