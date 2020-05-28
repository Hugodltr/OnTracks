package com.epf.ontracks.station


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.database.FavoriteDatabaseDAO
import com.epf.ontracks.stationslist.Station

class StationViewModelFactory(
    private val code: String,
    private val station: Station,
    private val type: String,
    private val dataSource: FavoriteDatabaseDAO,
    private val id: Long
): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StationViewModel::class.java)) {
            return StationViewModel(code = code, station = station, type = type, database = dataSource, id = id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}