package com.epf.ontracks.lineslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.network.LinesResult
import com.epf.ontracks.network.RatpApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class LinesListViewModel : ViewModel() {

    private val _lines = MutableLiveData<LinesResult>()
    val lines : LiveData<LinesResult>
        get() = _lines

    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getLines()
    }

    private fun getLines() {
        coroutineScope.launch {
            val getPropertiesDeferred = RatpApi.retrofitService.getLines()

            try {
                val res = getPropertiesDeferred.await()
                _lines.value = res
            } catch (e: Exception) {
                //_response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
