package com.epf.ontracks.lineslist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.R
import com.epf.ontracks.network.LineWithTraffic
import java.util.*

@BindingAdapter("lineImage")
fun ImageView.setLineImage(item: LineWithTraffic?) {
    item?.let {
        if(item.name.toLowerCase(Locale.ROOT).contains("métro")) {
            setImageResource(
                resources.getIdentifier("com.epf.ontracks:drawable/_m${item.code}genrvb", null, null)
            )
        } else if(item.name.toLowerCase(Locale.ROOT).contains("rer")) {
            setImageResource(
                resources.getIdentifier("com.epf.ontracks:drawable/_rer${item.code.toLowerCase(Locale.ROOT)}genrvb", null, null)
            )
        } else if(item.name.toLowerCase(Locale.ROOT).contains("tramway")) {
            setImageResource(
                resources.getIdentifier("com.epf.ontracks:drawable/_t${item.code.toLowerCase(Locale.ROOT)}genrvb", null, null)
            )
        } else if(item.name.toLowerCase(Locale.ROOT).contains("bus")) {
            val image = resources.getIdentifier("com.epf.ontracks:drawable/__${item.code.toLowerCase(Locale.ROOT)}genrvb", null, null)
            if(image != 0) {
                setImageResource(image)
            } else {
                setImageResource(R.drawable.bus)
            }
        } else if(item.name.toLowerCase(Locale.ROOT).contains("noctilien")) {
            setImageResource(
                resources.getIdentifier("com.epf.ontracks:drawable/_noct_${item.code.toLowerCase(Locale.ROOT)}_genrvb", null, null)
            )
        }
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
fun ImageView.setTrafficStateImage(item: LineWithTraffic?) {
    item?.let {
        setImageResource(when (item.slug) {
            "critical" -> R.drawable.ic_critical
            "normal_trav" -> R.drawable.ic_alert
            "normal" -> R.drawable.ic_normal
            else -> android.R.color.transparent
        })
    }
}


@BindingAdapter("headerShowMetros")
fun TextView.setMetrosHeaderTextString(show: Boolean) {
    if(show) {
        text = "Metros"
    } else {
        text = ""
    }
}


@BindingAdapter("headerShowRers")
fun TextView.setRersHeaderTextString(show: Boolean) {
    if(show) {
        text = "Rers"
    } else {
        text = ""
    }
}

@BindingAdapter("headerShowTramways")
fun TextView.setTramwaysHeaderTextString(show: Boolean) {
    if(show) {
        text = "Tramways"
    } else {
        text = ""
    }
}

@BindingAdapter("headerShowBuses")
fun TextView.setBusesHeaderTextString(show: Boolean) {
    if(show) {
        text = "Buses"
    } else {
        text = ""
    }
}

@BindingAdapter("headerShowNoctiliens")
fun TextView.setNoctiliensHeaderTextString(show: Boolean) {
    if(show) {
        text = "Noctiliens"
    } else {
        text = ""
    }
}