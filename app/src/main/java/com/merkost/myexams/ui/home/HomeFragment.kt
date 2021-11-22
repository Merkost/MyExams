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

    private val handler: Handler = Handler()
    private var runnable: Runnable? = null
    private val EVENT_DATE_TIME = "2021-12-31 10:30:00"
    private val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun countDownStart() {
        runnable = object : Runnable {
            override fun run() {
                try {
                    handler.postDelayed(this, 1000)
                    val dateFormat = SimpleDateFormat(DATE_FORMAT)
                    val event_date: Date = dateFormat.parse(EVENT_DATE_TIME)
                    val current_date = Date()
                    if (!current_date.after(event_date)) {
                        val diff: Long = event_date.getTime() - current_date.getTime()
                        val Days = diff / (24 * 60 * 60 * 1000)
                        val Hours = diff / (60 * 60 * 1000) % 24
                        val Minutes = diff / (60 * 1000) % 60
                        val Seconds = diff / 1000 % 60
                        //
                        binding.cdnDays.text = String.format("%02d", Days)
                        binding.cdnHours.text = String.format("%02d", Hours)
                        binding.cdnMins.text = String.format("%02d", Minutes)
                    } else {
                        /*linear_layout_1!!.visibility = View.VISIBLE
                        linear_layout_2.setVisibility(View.GONE)*/
                        runnable?.let { handler.removeCallbacks(it) }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        handler.postDelayed(runnable as Runnable, 0)
    }

    override fun onStop() {
        super.onStop()
        runnable?.let { handler.removeCallbacks(it) }
    }
}