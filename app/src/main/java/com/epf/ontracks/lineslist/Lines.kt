package com.epf.ontracks.lineslist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Line (
    val code: String,
    val name: String,
    val directions: String,
    val id: String
) : Parcelable

// all lines
data class LinesResult(
    val result: Lines
)

@Parcelize
data class Lines(
    val metros: List<Line>,
    val rers: List<Line>,
    val tramways: List<Line>,
    val buses: List<Line>,
    val noctiliens: List<Line>
) : Parcelable



