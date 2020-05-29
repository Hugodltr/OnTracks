package com.epf.ontracks.overview

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.database.StationEntity

@BindingAdapter("favStationName")
fun TextView.setFavStationNameString(item: StationEntity?) {
    item?.let {
        text = item.stationName
    }
}

@BindingAdapter("stationLineImage")
fun ImageView.setLineImage(item: StationEntity?) {
    item?.let {
        if(item.type == "metros") {
            setImageResource(
                resources.getIdentifier("com.epf.ontracks:drawable/_m${item.code}genrvb", null, null)
            )
        }
    }
}