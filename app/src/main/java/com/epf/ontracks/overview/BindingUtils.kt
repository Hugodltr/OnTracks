package com.epf.ontracks.overview

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.epf.ontracks.R
import com.epf.ontracks.database.StationEntity
import java.util.*

@BindingAdapter("favStationName")
fun TextView.setFavStationNameString(item: StationEntity?) {
    item?.let {
        text = item.stationName
    }
}

@BindingAdapter("stationLineImage")
fun ImageView.setLineImage(item: StationEntity?) {
    item?.let {
        when(item.type) {
            "metros" -> setImageResource(resources.getIdentifier("com.epf.ontracks:drawable/_m${item.code}genrvb", null, null))
            "tramways" -> setImageResource(resources.getIdentifier("com.epf.ontracks:drawable/_t${item.code.toLowerCase(Locale.ROOT)}genrvb", null, null))
            "buses" -> {
                val image = resources.getIdentifier("com.epf.ontracks:drawable/__${item.code.toLowerCase(Locale.ROOT)}genrvb", null, null)
                if(image != 0) {
                    setImageResource(image)
                } else {
                    setImageResource(R.drawable.bus)
                }
            }
            "noctiliens" -> setImageResource(resources.getIdentifier("com.epf.ontracks:drawable/_noct_${item.code.toLowerCase(Locale.ROOT)}_genrvb", null, null))
            else -> setImageResource(resources.getIdentifier("com.epf.ontracks:drawable/_rer${item.code.toLowerCase(Locale.ROOT)}genrvb", null, null))
        }
    }
}