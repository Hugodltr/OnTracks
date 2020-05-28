package com.epf.ontracks.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.database.FavoriteDatabaseDAO
import com.epf.ontracks.database.StationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class OverviewViewModel(database: FavoriteDatabaseDAO) : ViewModel() {

    // live data
    private val _navigateToLines = MutableLiveData<Boolean>()
    val navigateToLines : LiveData<Boolean>
        get() = _navigateToLines

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    // navigation
    private val _navigateToStation = MutableLiveData<Boolean>()
    val navigateToStation: LiveData<Boolean>
        get() = _navigateToStation

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

    fun navigateToStation(navigating: Boolean, station: StationEntity?) {
        _station.value = station
        _navigateToStation.value = navigating
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
