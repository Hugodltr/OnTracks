package com.epf.ontracks.network

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