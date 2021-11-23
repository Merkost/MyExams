package com.merkost.myexams.ui.home

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.merkost.myexams.R
import com.merkost.myexams.databinding.ClassesItemBinding
import com.merkost.myexams.model.entity.Class

class ClassesRVadapter : RecyclerView.Adapter<ClassesRVadapter.RecyclerItemViewHolder>() {

    private var data: List<Class> = arrayListOf()

    fun setData(data: List<Class>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val itemBinding = ClassesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(val binding: ClassesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Class) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.imageView.setImageResource(R.drawable.ic_launcher_background)
                binding.title.text = "Название"

                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, "on click: ${data.title}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}