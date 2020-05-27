package com.epf.ontracks.station


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.stationslist.Station

class StationViewModelFactory(private val code: String, private val station: Station, private val type: String): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StationViewModel::class.java)) {
            return StationViewModel(code, station, type) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}