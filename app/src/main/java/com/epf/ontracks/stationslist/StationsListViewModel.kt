package com.epf.ontracks.stationslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.lineslist.Line
import com.epf.ontracks.network.LineTraffic
import com.epf.ontracks.network.RatpApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StationsListViewModel : ViewModel() {
    // live data
    private val _stations = MutableLiveData<Stations>()
    val stations : LiveData<Stations>
        get() = _stations

    // route parameters
    private val _line = MutableLiveData<Line>()
    val line : LiveData<Line>
        get() = _line

    private val _traffic = MutableLiveData<LineTraffic>()
    val traffic : LiveData<LineTraffic>
        get() = _traffic

    private lateinit var type: String

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getStations()
    }

    private fun getStations() {
        coroutineScope.launch {
            val getLinesDeferred = RatpApi.retrofitService.getStations(type, _line.value!!.code)

            try {
                val resStations = getLinesDeferred.await()
                _stations.value = resStations.result
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
