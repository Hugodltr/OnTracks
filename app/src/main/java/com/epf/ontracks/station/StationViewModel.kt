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

class StationViewModel : ViewModel() {
    // live data
    private val _station = MutableLiveData<Station>()
    val station : LiveData<Station>
        get() = _station

    private val _schedules = MutableLiveData<Schedules>()
    val schedules : LiveData<Schedules>
        get() = _schedules

    private val _way = MutableLiveData<Char>()
    val way : LiveData<Char>
        get() = _way

    // route parameters
    private lateinit var type: String
    private lateinit var code: String

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getStation()
    }

    private fun getStation() {
        coroutineScope.launch {
            val getStationDeferred = RatpApi.retrofitService.getStation(type, code, _station.value!!.slug, _way.value)

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
