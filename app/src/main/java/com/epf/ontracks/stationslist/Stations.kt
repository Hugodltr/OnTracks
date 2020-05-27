package com.epf.ontracks.stationslist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class StationsResult(
    val result: Stations
)

data class Stations(
    val stations: List<Station>
)

@Parcelize
data class Station(
    val name: String,
    val slug: String
) : Parcelable