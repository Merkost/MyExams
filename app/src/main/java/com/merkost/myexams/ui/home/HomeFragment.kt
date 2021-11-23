package com.merkost.myexams.ui.home

import android.icu.text.DateFormatSymbols.FORMAT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import com.merkost.myexams.model.entity.Class
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.merkost.myexams.R
import com.merkost.myexams.databinding.FragmentHomeBinding
import android.os.CountDownTimer
import java.util.concurrent.TimeUnit
import android.os.Handler

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ClassesRVadapter

    val testData = mutableListOf(Class()).apply {
        (1..5).forEach { add(Class()) }
    }

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
        initClassesRV()

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    private fun initClassesRV() {
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.classesRV.layoutManager = linearLayoutManager
        adapter = ClassesRVadapter()
        binding.classesRV.adapter = adapter

        adapter.setData(testData)

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