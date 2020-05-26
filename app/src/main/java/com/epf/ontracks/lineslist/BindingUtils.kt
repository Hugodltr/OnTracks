package com.epf.ontracks.lineslist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.R

@BindingAdapter("lineImage")
fun ImageView.setLineImage(item: Line) {
    setImageResource(when (item.id) {
        "62" -> R.drawable.ic_line_metro_1
        else -> R.drawable.ic_line_metro
    })
}

@BindingAdapter("lineName")
fun TextView.setLineNameString(item: Line) {
    text = item.name
}

@BindingAdapter("lineDirections")
fun TextView.setLineDirectionsString(item: Line) {
    text = item.directions
}