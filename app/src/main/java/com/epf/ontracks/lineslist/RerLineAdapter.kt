package com.epf.ontracks.lineslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.epf.ontracks.databinding.ListItemRerLineBinding
import com.epf.ontracks.network.LineWithTraffic


class RerLineAdapter(private val clickListener: RerLineListener) : ListAdapter<LineWithTraffic, RerLineAdapter.ViewHolder>(RerLineDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemRerLineBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: LineWithTraffic, clickListener: RerLineListener) {
            binding.line = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemRerLineBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class RerLineDiffCallback : DiffUtil.ItemCallback<LineWithTraffic>() {
    override fun areItemsTheSame(oldItem: LineWithTraffic, newItem: LineWithTraffic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LineWithTraffic, newItem: LineWithTraffic): Boolean {
        return oldItem.id == newItem.id && newItem.message == newItem.message && oldItem.slug == oldItem.slug
    }
}

class RerLineListener(val clickListener: (line: LineWithTraffic, lineType: String) -> Unit) {
    fun onClick(line: LineWithTraffic, lineType: String) = clickListener(line, lineType)
}
