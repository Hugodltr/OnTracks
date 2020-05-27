package com.epf.ontracks.lineslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.epf.ontracks.databinding.ListItemLineBinding


class LineAdapter(val clickListener: LineListener) : ListAdapter<Line, LineAdapter.ViewHolder>(LineDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemLineBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Line,clickListener: LineListener) {
            binding.line = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemLineBinding.inflate(layoutInflater, parent, false)

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

class LineListener(val clickListener: (line: Line, lineType: String) -> Unit) {
    fun onClick(line: Line, lineType: String) = clickListener(line, lineType)
}