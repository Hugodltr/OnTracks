package com.epf.ontracks.lineslist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.R
import com.epf.ontracks.network.LineWithTraffic

@BindingAdapter("lineImage")
fun ImageView.setLineImage(item: LineWithTraffic?) {
    item?.let {
        setImageResource(when (item.id) {
            "62" -> R.drawable.ic_line_metro_1
            "77" -> R.drawable.ic_ligne_rer_a
            else -> R.drawable.ic_line_metro
        })
    }
}

@BindingAdapter("lineName")
fun TextView.setLineNameString(item: LineWithTraffic?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("lineDirections")
fun TextView.setLineDirectionsString(item: LineWithTraffic?) {
    item?.let {
        text = item.directions
    }
}

@BindingAdapter("trafficStateImage")
fun ImageView.setTrafficStateImage(item: LineWithTraffic) {
    item.let {
        setImageResource(when (item.slug) {
            "critical" -> R.drawable.ic_critical
            "normal_trav" -> R.drawable.ic_alert
            "normal" -> R.drawable.ic_normal
            else -> android.R.color.transparent
        })
    }
}