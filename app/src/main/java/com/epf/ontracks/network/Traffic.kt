package com.epf.ontracks.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class LineTraffic(
    val line: String,
    val slug: String,
    val title: String,
    val message: String
)

// get all traffic
data class AllTrafficResult(
    val result: Traffic
)

data class Traffic(
    val metros: List<LineTraffic>,
    val rers: List<LineTraffic>,
    val tramways: List<LineTraffic>
)


@Parcelize
data class LineWithTraffic(
    val code: String,
    val name: String,
    val directions: String,
    val id: String,
    val slug: String,
    val title: String,
    val message: String

) : Parcelable


class LinesWithTraffic {

    companion object Lines {
        var metros = listOf<LineWithTraffic>()
        var rers = listOf<LineWithTraffic>()
        var tramways = listOf<LineWithTraffic>()
        var buses = listOf<LineWithTraffic>()
        var noctiliens = listOf<LineWithTraffic>()

        fun make(metros: List<LineWithTraffic>, rers: List<LineWithTraffic>, tramways: List<LineWithTraffic>, buses: List<LineWithTraffic>, noctiliens: List<LineWithTraffic>) {
            this.metros = metros
            this.rers = rers
            this.tramways = tramways
            this.buses = buses
            this.noctiliens = noctiliens
        }
    }
}