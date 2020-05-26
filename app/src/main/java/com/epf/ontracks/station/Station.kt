package com.epf.ontracks.station

data class StationResult(
    val result: Schedules
)

data class Schedules(
    val schedules: List<Schedule>
)

data class Schedule(
    val message: String,
    val destination: String
)