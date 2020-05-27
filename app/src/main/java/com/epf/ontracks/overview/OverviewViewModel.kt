package com.epf.ontracks.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OverviewViewModel : ViewModel() {

    // live data
    private val _navigateToLines = MutableLiveData<Boolean>()
    val navigateToLines : LiveData<Boolean>
        get() = _navigateToLines

    // navigation
    fun navigateToLines(navigating: Boolean) {
        _navigateToLines.value = navigating
    }
}
