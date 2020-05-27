package com.epf.ontracks.lineslist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.R

@BindingAdapter("lineImage")
fun ImageView.setLineImage(item: Line?) {
    item?.let {
        setImageResource(when (item.id) {
            "62" -> R.drawable.ic_line_metro_1
            "77" -> R.drawable.ic_ligne_rer_a
            else -> R.drawable.ic_line_metro
        })
    }
}

@BindingAdapter("lineName")
fun TextView.setLineNameString(item: Line?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("lineDirections")
fun TextView.setLineDirectionsString(item: Line?) {
    item?.let {
        text = item.directions
    }
}