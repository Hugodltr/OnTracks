package com.epf.ontracks.lineslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.epf.ontracks.databinding.LineItemViewBinding


class LineAdapter : ListAdapter<Line, LineAdapter.ViewHolder>(LineDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: LineItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Line) {
            binding.line = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LineItemViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class LineDiffCallback : DiffUtil.ItemCallback<Line>() {
    override fun areItemsTheSame(oldItem: Line, newItem: Line): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Line, newItem: Line): Boolean {
        return oldItem == newItem
    }
}