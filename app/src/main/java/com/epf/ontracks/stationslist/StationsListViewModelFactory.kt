package com.epf.ontracks.stationslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.network.LineWithTraffic

@Suppress("UNCHECKED_CAST")
class StationsListViewModelFactory(private val line: LineWithTraffic, private val type: String): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StationsListViewModel::class.java)) {
            return StationsListViewModel(line, type) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}