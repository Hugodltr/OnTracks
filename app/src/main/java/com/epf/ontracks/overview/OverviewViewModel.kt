package com.epf.ontracks.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.database.FavoriteDatabaseDAO
import com.epf.ontracks.database.StationEntity

class OverviewViewModel(database: FavoriteDatabaseDAO) : ViewModel() {

    // navigation
    private val _navigateToLines = MutableLiveData<Boolean>()
    val navigateToLines : LiveData<Boolean>
        get() = _navigateToLines

    private val _navigateToStation = MutableLiveData<Boolean>()
    val navigateToStation: LiveData<Boolean>
        get() = _navigateToStation

    private val _navigateToScanner = MutableLiveData<Boolean>()
    val navigateToScanner: LiveData<Boolean>
        get() = _navigateToScanner

    // navigation parameter
    private val _station = MutableLiveData<StationEntity>()
    val station: LiveData<StationEntity>
        get() = _station

    // database
    val favorites = database.getAllStations()

    // navigation
    fun navigateToLines(navigating: Boolean) {
        _navigateToLines.value = navigating
    }

    fun navigateToScanner(navigating: Boolean) {
        _navigateToScanner.value = navigating
    }

    fun navigateToStation(navigating: Boolean, station: StationEntity?) {
        _station.value = station
        _navigateToStation.value = navigating
    }
}
