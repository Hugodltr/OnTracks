package com.epf.ontracks.lineslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class LinesListViewModel(lines: Lines) : ViewModel() {

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
        _metros.value = lines.metros
        _rers.value = lines.rers
        _tramways.value = lines.tramways
        _buses.value = lines.buses
        _noctiliens.value = lines.noctiliens
    }

    // navigation
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
