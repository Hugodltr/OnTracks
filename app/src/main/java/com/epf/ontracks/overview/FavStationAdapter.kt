package com.epf.ontracks.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.epf.ontracks.database.StationEntity
import com.epf.ontracks.databinding.ListItemFavoriteBinding

class FavStationAdapter(val clickListener: FavStationListener) : ListAdapter<StationEntity, FavStationAdapter.ViewHolder>(FavStationDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: StationEntity,clickListener: FavStationListener) {
            binding.station = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFavoriteBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class FavStationDiffCallback : DiffUtil.ItemCallback<StationEntity>() {
    override fun areItemsTheSame(oldItem: StationEntity, newItem: StationEntity): Boolean {
        return oldItem.stationId == newItem.stationId
    }

    override fun areContentsTheSame(oldItem: StationEntity, newItem: StationEntity): Boolean {
        return oldItem == newItem
    }
}

class FavStationListener(val clickListener: (station: StationEntity) -> Unit) {
    fun onClick(station: StationEntity) = clickListener(station)
}
