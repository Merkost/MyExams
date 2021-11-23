package com.merkost.myexams.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.merkost.myexams.R
import com.merkost.myexams.databinding.ClassesItemBinding
import com.merkost.myexams.databinding.HomeworkItemBinding
import com.merkost.myexams.model.entity.Homework

class HomeworkRVadapter : RecyclerView.Adapter<HomeworkRVadapter.RecyclerItemViewHolder>() {

    private var data: List<Homework> = arrayListOf()

    fun setData(data: List<Homework>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val itemBinding =
            HomeworkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(val binding: HomeworkItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Homework) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.imageView.setImageResource(R.drawable.ic_launcher_background)
                binding.title.text = "Название"

                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, "on click: ${data.title}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}