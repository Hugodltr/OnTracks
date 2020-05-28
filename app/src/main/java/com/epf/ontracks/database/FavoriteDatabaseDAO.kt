package com.epf.ontracks.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteDatabaseDAO {
    @Insert
    fun insert(station: StationEntity): Long

    @Query("DELETE FROM favorite_stations_table WHERE stationId = :id")
    fun deleteById(id: Long)

    @Query("SELECT stationId FROM favorite_stations_table WHERE stations_code = :code AND station_type = :type AND station_slug = :slug")
    fun getStation(code: String, type: String, slug: String): Long?

    @Query("SELECT * FROM favorite_stations_table")
    fun getAllStations(): LiveData<List<StationEntity>>
}
