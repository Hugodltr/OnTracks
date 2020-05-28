package com.epf.ontracks.overview

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.database.StationEntity

@BindingAdapter("favStationName")
fun TextView.setFavStationNameString(item: StationEntity?) {
    item?.let {
        text = item.stationName
    }
}