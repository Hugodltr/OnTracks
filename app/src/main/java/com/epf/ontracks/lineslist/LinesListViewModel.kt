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
    private val _lines = MutableLiveData<List<LineWithTraffic>>()
    val lines: LiveData<List<LineWithTraffic>>
        get() = _lines

    // show lines
    private var _showLines = MutableLiveData<String>()
    val showLines: LiveData<String>
        get() = _showLines

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

    private val _lineButton= MutableLiveData<String>()
    val lineButton: LiveData<String>
        get() = _lineButton

    private var _ButtonMetro= MutableLiveData<Boolean>()
    val ButtonMetro: LiveData<Boolean>
        get() = _ButtonMetro





    init {
        _lines.value = LinesWithTraffic.metros
        _showLines.value = "metros"
    }

    // navigation
    fun navigateToStations(navigating: Boolean, line: LineWithTraffic?, lineType: String?) {
        _line.value = line
        _lineType.value = lineType
        _navigateToStations.value = navigating
    }

    // show lines
    fun showLines(linesType: String) {
        _lines.value = when(linesType) {
            "rers" -> LinesWithTraffic.rers
            "tramways" -> LinesWithTraffic.tramways
            "buses" -> LinesWithTraffic.buses
            "noctiliens" -> LinesWithTraffic.noctiliens
            else -> LinesWithTraffic.metros
        }
        _showLines.value = linesType
    }

    // search
    fun filterLines(query : String) {
        if(query.isEmpty()) {
            _lines.value = when(_showLines.value) {
                "rers" -> LinesWithTraffic.rers
                "tramways" -> LinesWithTraffic.tramways
                "buses" -> LinesWithTraffic.buses
                "noctiliens" -> LinesWithTraffic.noctiliens
                else -> LinesWithTraffic.metros
            }
        } else {
            when(_showLines.value) {
                "rers" -> filterRers(query)
                "tramways" -> filterTramways(query)
                "buses" -> filterBuses(query)
                "noctiliens" -> filterNoctiliens(query)
                else -> filterMetros(query)
            }
        }
    }

    private fun filterNoctiliens(query: String) {
        val filteredLines: MutableList<LineWithTraffic> = ArrayList()

        LinesWithTraffic.noctiliens.forEach { noctiliens ->
            if (noctiliens.name.toLowerCase(Locale.ROOT)
                    .contains(query) || noctiliens.directions.toLowerCase(
                    Locale.ROOT
                ).contains(query)
            ) {
                filteredLines.add(noctiliens)
            }
        }

        _lines.value = filteredLines
    }

    private fun filterBuses(query: String) {
        val filteredLines: MutableList<LineWithTraffic> = ArrayList()

        LinesWithTraffic.buses.forEach { bus ->
            if (bus.name.toLowerCase(Locale.ROOT).contains(query) || bus.directions.toLowerCase(
                    Locale.ROOT
                ).contains(query)
            ) {
                filteredLines.add(bus)
            }
        }

        _lines.value = filteredLines
    }

    private fun filterTramways(query: String) {
        val filteredLines: MutableList<LineWithTraffic> = ArrayList()

        LinesWithTraffic.tramways.forEach { tramway ->
            if (tramway.name.toLowerCase(Locale.ROOT)
                    .contains(query) || tramway.directions.toLowerCase(
                    Locale.ROOT
                ).contains(query)
            ) {
                filteredLines.add(tramway)
            }
        }

        _lines.value = filteredLines
    }

    private fun filterRers(query: String) {
        val filteredLines: MutableList<LineWithTraffic> = ArrayList()

        LinesWithTraffic.rers.forEach { rer ->
            if (rer.name.toLowerCase(Locale.ROOT).contains(query) || rer.directions.toLowerCase(
                    Locale.ROOT
                ).contains(query)
            ) {
                filteredLines.add(rer)
            }
        }

        _lines.value = filteredLines
    }

    private fun filterMetros(query: String) {
        val filteredLines: MutableList<LineWithTraffic> = ArrayList()

        LinesWithTraffic.metros.forEach { metro ->
            if (metro.name.toLowerCase(Locale.ROOT).contains(query) || metro.directions.toLowerCase(
                    Locale.ROOT
                ).contains(query)
            ) {
                filteredLines.add(metro)
            }
        }

        _lines.value = filteredLines
    }

}
