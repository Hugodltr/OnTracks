package com.epf.ontracks.stationslist

import android.util.Log
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

class StationsListViewModel(val line: Line, val type: String) : ViewModel() {

    // live data
    private val _stations = MutableLiveData<Stations>()
    val stations : LiveData<Stations>
        get() = _stations

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getStations()
    }

    private fun getStations() {
        coroutineScope.launch {
            val getLinesDeferred = RatpApi.retrofitService.getStations(type, line.code)

            try {
                val resStations = getLinesDeferred.await()
                _stations.value = resStations.result
                Log.i("Hugo", resStations.toString())
            } catch (e: Exception) {
                Log.i("Hugo", "Failure: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
