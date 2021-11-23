package com.merkost.myexams.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merkost.myexams.databinding.FragmentHomeBinding
import com.merkost.myexams.model.entity.Class
import com.merkost.myexams.model.entity.Homework


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var classesAdapter: ClassesRVadapter
    private lateinit var homeworkAdapter: HomeworkRVadapter

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
        initHomeworkRV()

        return root
    }

    private fun initHomeworkRV() {
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.homeworkRV.layoutManager = linearLayoutManager
        homeworkAdapter = HomeworkRVadapter()
        binding.homeworkRV.adapter = homeworkAdapter

        homeworkAdapter.setData(homeViewModel.homeworkTestData)
    }

    private fun initClassesRV() {
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.classesRV.layoutManager = linearLayoutManager
        classesAdapter = ClassesRVadapter()
        binding.classesRV.adapter = classesAdapter

        classesAdapter.setData(homeViewModel.classesTestData)

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