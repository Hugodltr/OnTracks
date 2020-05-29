package com.epf.ontracks.lineslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.network.LineWithTraffic
import com.epf.ontracks.network.LinesWithTraffic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class LinesListViewModel : ViewModel() {

    // live data
    private val _metros = MutableLiveData<List<LineWithTraffic>>()
    val metros: LiveData<List<LineWithTraffic>>
        get() = _metros

    private val _rers = MutableLiveData<List<LineWithTraffic>>()
    val rers: LiveData<List<LineWithTraffic>>
        get() = _rers

    private val _tramways = MutableLiveData<List<LineWithTraffic>>()
    val tramways: LiveData<List<LineWithTraffic>>
        get() = _tramways

    private val _buses = MutableLiveData<List<LineWithTraffic>>()
    val buses: LiveData<List<LineWithTraffic>>
        get() = _buses

    private val _noctiliens = MutableLiveData<List<LineWithTraffic>>()
    val noctiliens: LiveData<List<LineWithTraffic>>
        get() = _noctiliens

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    // navigation
    private val _navigateToStations = MutableLiveData<Boolean>()
    val navigateToStations: LiveData<Boolean>
        get() = _navigateToStations

    // navigation parameter
    private val _line = MutableLiveData<LineWithTraffic>()
    val line: LiveData<LineWithTraffic>
        get() = _line

    private val _lineType = MutableLiveData<String>()
    val lineType: LiveData<String>
        get() = _lineType

    init {
        _metros.value = LinesWithTraffic.metros
        //_rers.value = lines.rers
        //_tramways.value = lines.tramways
        //_buses.value = lines.buses
        //_noctiliens.value = lines.noctiliens
    }

    // navigation
    fun navigateToStations(navigating: Boolean, line: LineWithTraffic?, lineType: String?) {
        _navigateToStations.value = navigating
        _line.value = line
        _lineType.value = lineType
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
