package com.merkost.myexams.ui.home

import android.icu.text.DateFormatSymbols.FORMAT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.merkost.myexams.R
import com.merkost.myexams.databinding.FragmentHomeBinding
import android.os.CountDownTimer
import java.util.concurrent.TimeUnit
import android.os.Handler

import android.widget.LinearLayout
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        countDownStart()

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    private fun countDownStart() {
        binding.cdnDays.text = homeViewModel.days.value
        binding.cdnHours.text = homeViewModel.hours.value
        binding.cdnMins.text = homeViewModel.minutes.value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}