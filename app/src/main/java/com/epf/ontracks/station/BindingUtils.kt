package com.epf.ontracks.station

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("scheduleTime")
fun TextView.setScheduleTimeString(item: Schedule?) {
    item?.let {
        text = item.message
    }
}

@BindingAdapter("scheduleDirection")
fun TextView.setScheduleDirectionString(item: Schedule?) {
    item?.let {
        text = item.destination
    }
}