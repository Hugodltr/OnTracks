package com.epf.ontracks.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_stations_table")
data class StationEntity(
    @PrimaryKey(autoGenerate = true)
    var stationId: Long = 0L,

    @ColumnInfo(name = "station_type")
    var type: String = "",

    @ColumnInfo(name = "stations_code")
    val code: String = "",

    @ColumnInfo(name = "station_name")
    var stationName: String = "",

    @ColumnInfo(name = "station_slug")
    var stationSlug: String = ""
)
