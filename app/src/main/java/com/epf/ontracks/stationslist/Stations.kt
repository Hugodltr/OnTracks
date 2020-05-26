package com.epf.ontracks.stationslist

data class StationsResult(
    val result: Stations
)

data class Stations(
    val stations: List<Station>
)

data class Station(
    val name: String,
    val slug: String
)