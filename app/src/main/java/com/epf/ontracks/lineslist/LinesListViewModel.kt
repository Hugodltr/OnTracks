package com.epf.ontracks.lineslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.network.LineTraffic
import com.epf.ontracks.network.RatpApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class LinesListViewModel : ViewModel() {

    // live data
    private val _metros = MutableLiveData<List<Line>>()
    val metros: LiveData<List<Line>>
        get() = _metros

    private val _rers = MutableLiveData<List<Line>>()
    val rers: LiveData<List<Line>>
        get() = _rers

    private val _tramways = MutableLiveData<List<Line>>()
    val tramways: LiveData<List<Line>>
        get() = _tramways

    private val _buses = MutableLiveData<List<Line>>()
    val buses: LiveData<List<Line>>
        get() = _buses

    private val _noctiliens = MutableLiveData<List<Line>>()
    val noctiliens: LiveData<List<Line>>
        get() = _noctiliens

    private val _trafficMetros = MutableLiveData<List<LineTraffic>>()
    val trafficMetro: LiveData<List<LineTraffic>>
        get() = _trafficMetros

    private val _trafficRers = MutableLiveData<List<LineTraffic>>()
    val trafficRers: LiveData<List<LineTraffic>>
        get() = _trafficRers

    private val _trafficTramways = MutableLiveData<List<LineTraffic>>()
    val trafficTramways: LiveData<List<LineTraffic>>
        get() = _trafficTramways

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    // navigation
    private val _navigateToStations = MutableLiveData<Boolean>()
    val navigateToStations: LiveData<Boolean>
        get() = _navigateToStations

    // navigation parameter
    private val _line = MutableLiveData<Line>()
    val line: LiveData<Line>
        get() = _line

    private val _lineType = MutableLiveData<String>()
    val lineType: LiveData<String>
        get() = _lineType

    init {
        getLines()
    }

    private fun getLines() {
        coroutineScope.launch {
            val getLinesDeferred = RatpApi.retrofitService.getLines()
            val getTrafficDeferred = RatpApi.retrofitService.getAllTraffic()

            try {
                val resLines = getLinesDeferred.await()
                _metros.value = resLines.result.metros
                _rers.value = resLines.result.rers
                _tramways.value = resLines.result.tramways
                _buses.value = resLines.result.buses
                _noctiliens.value = resLines.result.noctiliens

                val resTraffic = getTrafficDeferred.await()
                _trafficMetros.value = resTraffic.result.metros
                _trafficRers.value = resTraffic.result.rers
                _trafficTramways.value = resTraffic.result.tramways
            } catch (e: Exception) {
                //_response.value = "Failure: ${e.message}"
            }
        }
    }

    fun navigateToStations(navigating: Boolean, line: Line?, lineType: String?) {
        _navigateToStations.value = navigating
        _line.value = line
        _lineType.value = lineType
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
