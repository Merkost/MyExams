package com.merkost.myexams.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merkost.myexams.R
import com.merkost.myexams.databinding.FragmentDashboardBinding
import com.merkost.myexams.ui.home.ClassesRVadapter
import com.merkost.myexams.ui.home.HomeworkRVadapter

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dayHomeworkAdapter: DayHomeworkRVadapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRV()

        return root
    }

    private fun initRV() {
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.dayHomeworkRV.layoutManager = linearLayoutManager
        dayHomeworkAdapter = DayHomeworkRVadapter()
        binding.dayHomeworkRV.adapter = dayHomeworkAdapter
        dayHomeworkAdapter.setData(dashboardViewModel.dayHomeworkTestData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}