package com.epf.ontracks.lineslist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.R
import com.epf.ontracks.network.LineWithTraffic
import java.util.*

@BindingAdapter("metroLineImage")
fun ImageView.setMetroLineImage(item: LineWithTraffic?) {
    item?.let {
        setImageResource(
            resources.getIdentifier("com.epf.ontracks:drawable/_m${item.code}genrvb", null, null)
        )
    }
}

@BindingAdapter("rerLineImage")
fun ImageView.setRerLineImage(item: LineWithTraffic?) {
    item?.let {
        setImageResource(
            resources.getIdentifier("com.epf.ontracks:drawable/_rer${item.code.toLowerCase(Locale.ROOT)}genrvb", null, null)
        )
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