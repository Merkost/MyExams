package com.merkost.myexams.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merkost.myexams.model.entity.Homework

class DashboardViewModel : ViewModel() {

    val dayHomeworkTestData: List<Homework> = mutableListOf(Homework()).apply{
        (1..12).forEach { add(Homework()) }
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}