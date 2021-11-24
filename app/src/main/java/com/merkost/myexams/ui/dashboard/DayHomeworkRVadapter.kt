package com.merkost.myexams.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.merkost.myexams.databinding.ClassesItemBinding
import com.merkost.myexams.databinding.DayHomeworkItemBinding
import com.merkost.myexams.model.entity.Class
import com.merkost.myexams.model.entity.Homework
import com.merkost.myexams.ui.home.ClassesRVadapter
import android.content.pm.PackageManager
import android.content.Intent
import android.net.Uri
import android.content.ComponentName


import android.R
import android.content.res.Resources


class DayHomeworkRVadapter : RecyclerView.Adapter<DayHomeworkRVadapter.RecyclerItemViewHolder>() {

    private var data: List<Homework> = arrayListOf()

    fun setData(data: List<Homework>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val itemBinding = DayHomeworkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(val binding: DayHomeworkItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Homework) {
            if (layoutPosition != RecyclerView.NO_POSITION) {

                binding.imageView.setImageResource(com.merkost.myexams.R.drawable.ic_launcher_background)
                binding.teacherTV.text = data.teacher
                binding.titleTV.text = data.finish

                binding.skypeBtn.setOnClickListener {
                    initiateSkypeUri(binding.skypeBtn.context, data.teacher)

                }
            }
        }
    }
}

/**
 * Determine whether the Skype for Android client is installed on this device.
 */
fun isSkypeClientInstalled(myContext: Context): Boolean {
    val myPackageMgr: PackageManager = myContext.packageManager
    try {
        myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES)
    } catch (e: PackageManager.NameNotFoundException) {
        return false
    }
    return true
}

/**
 * Install the Skype client through the market: URI scheme.
 */
fun goToMarket(myContext: Context) {
    val marketUri: Uri = Uri.parse("market://details?id=com.skype.raider")
    val myIntent = Intent(Intent.ACTION_VIEW, marketUri)
    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    myContext.startActivity(myIntent)
    return
}

/**
 * Initiate the actions encoded in the specified URI.
 */
fun initiateSkypeUri(myContext: Context, mySkypeUri: String?) {

    // Make sure the Skype for Android client is installed.
    if (!isSkypeClientInstalled(myContext)) {
        goToMarket(myContext)
        return
    }

    // Create the Intent from our Skype URI.
    val skypeUri = Uri.parse(mySkypeUri)
    val myIntent = Intent(Intent.ACTION_VIEW, skypeUri)

    // Restrict the Intent to being handled by the Skype for Android client only.
    myIntent.component = ComponentName("com.skype.raider", "com.skype.raider.Main")
    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

    // Initiate the Intent. It should never fail because you've already established the
    // presence of its handler (although there is an extremely minute window where that
    // handler can go away).
    myContext.startActivity(myIntent)
    return
}