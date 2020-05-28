package com.epf.ontracks.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epf.ontracks.database.FavoriteDatabaseDAO


class OverviewViewModelFactory(private val dataSource: FavoriteDatabaseDAO) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
