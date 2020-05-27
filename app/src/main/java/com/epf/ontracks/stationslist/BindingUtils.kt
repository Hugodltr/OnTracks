package com.epf.ontracks.stationslist

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("stationName")
fun TextView.setStationNameString(item: Station?) {
    item?.let {
        text = item.name
    }
}