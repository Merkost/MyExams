package com.merkost.myexams.ui.home

import android.os.Handler
import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.MutableLiveData
import com.merkost.myexams.model.entity.Class
import com.merkost.myexams.model.entity.Homework


class HomeViewModel : ViewModel() {

    val days = MutableStateFlow<String>("00")
    val hours = MutableStateFlow<String>("00")
    val minutes = MutableStateFlow<String>("00")

    private val EVENT_DATE_TIME = "2021-12-31 10:30:00"
    private val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    init {
        countDownStart()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val classesTestData = mutableListOf(Class()).apply {
        (1..5).forEach { add(Class()) }
    }

    val homeworkTestData = mutableListOf(Homework()).apply {
        (1..5).forEach { add(Homework()) }
    }



    private fun countDownStart() {
        viewModelScope.launch {
            try {


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
                    days.value = String.format("%02d", Days)
                    hours.value = String.format("%02d", Hours)
                    minutes.value = String.format("%02d", Minutes)
                } else {
                    /*linear_layout_1!!.visibility = View.VISIBLE
                        linear_layout_2.setVisibility(View.GONE)*/

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}