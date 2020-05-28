package com.epf.ontracks.stationslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.lineslist.Line
import com.epf.ontracks.network.RatpApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StationsListViewModel(val line: Line, val type: String) : ViewModel() {

    // live data
    private val _stations = MutableLiveData<List<Station>>()
    val stations : LiveData<List<Station>>
        get() = _stations

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    // navigation
    private val _navigateToStation = MutableLiveData<Boolean>()
    val navigateToStation: LiveData<Boolean>
        get() = _navigateToStation

    // navigation parameter
    private val _station = MutableLiveData<Station>()
    val station: LiveData<Station>
        get() = _station

    init {
        getStations()
    }

    private fun getStations() {
        coroutineScope.launch {
            val getLinesDeferred = RatpApi.retrofitService.getStationsAsync(type, line.code)

            try {
                val resStations = getLinesDeferred.await()
                _stations.value = resStations.result.stations
            } catch (e: Exception) {
                Log.i("Hugo", "Failure: ${e.message}")
            }
        }
    }

    fun navigateToStation(navigating: Boolean, station: Station?) {
        _station.value = station
        _navigateToStation.value = navigating
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
