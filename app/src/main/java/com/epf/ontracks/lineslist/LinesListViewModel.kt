package com.epf.ontracks.lineslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.network.LineWithTraffic
import com.epf.ontracks.network.LinesWithTraffic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.*
import kotlin.collections.ArrayList


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
    private val _line = MutableLiveData<LineWithTraffic>()
    val line: LiveData<LineWithTraffic>
        get() = _line

    private val _lineType = MutableLiveData<String>()
    val lineType: LiveData<String>
        get() = _lineType


    init {
        _metros.value = LinesWithTraffic.metros
        _rers.value = LinesWithTraffic.rers
        //_tramways.value = lines.tramways
        //_buses.value = lines.buses
        //_noctiliens.value = lines.noctiliens
    }

    // navigation
    fun navigateToStations(navigating: Boolean, line: LineWithTraffic?, lineType: String?) {
        _line.value = line
        _lineType.value = lineType
        _navigateToStations.value = navigating
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun filterLines(query : String) {
        if(query.isEmpty()) {
            _metros.value = LinesWithTraffic.metros
            _rers.value = LinesWithTraffic.rers
        } else {
            val metrosRes: MutableList<LineWithTraffic> = ArrayList()
            val rerRes: MutableList<LineWithTraffic> = ArrayList()

            LinesWithTraffic.metros.forEach { metro ->
            if(metro.name.toLowerCase(Locale.ROOT).contains(query) || metro.directions.toLowerCase(Locale.ROOT).contains(query)) {
                    metrosRes.add(metro)
                }
            }

            LinesWithTraffic.rers.forEach { rer ->
                if(rer.name.toLowerCase(Locale.ROOT).contains(query) || rer.directions.toLowerCase(Locale.ROOT).contains(query)) {
                    rerRes.add(rer)
                }
            }

            _metros.value = metrosRes
            _rers.value = rerRes
        }

    }

}
