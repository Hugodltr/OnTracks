package com.epf.ontracks.station

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.network.RatpApi
import com.epf.ontracks.stationslist.Station
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StationViewModel(val code: String, val station: Station, val type: String): ViewModel() {

    private val _schedules = MutableLiveData<Schedules>()
    val schedules : LiveData<Schedules>
        get() = _schedules

    private val _way = MutableLiveData<Char>()
    val way : LiveData<Char>
        get() = _way

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getStation()
    }

    private fun getStation() {
        coroutineScope.launch {
            val getStationDeferred = RatpApi.retrofitService.getStation(type, code, station.slug, _way.value)

            try {
                val resStation = getStationDeferred.await()
                _schedules.value = resStation.result
            } catch (e: Exception) {
                //_response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
