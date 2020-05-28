package com.epf.ontracks.lineslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class LinesListViewModelFactory(private val lines: Lines): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LinesListViewModel::class.java)) {
            return LinesListViewModel(lines) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}