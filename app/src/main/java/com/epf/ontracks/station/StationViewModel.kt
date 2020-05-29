package com.epf.ontracks.station

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.database.FavoriteDatabaseDAO
import com.epf.ontracks.database.StationEntity
import com.epf.ontracks.network.RatpApi
import com.epf.ontracks.stationslist.Station
import kotlinx.coroutines.*

class StationViewModel(val code: String, val station: Station, val type: String, val database: FavoriteDatabaseDAO, var id: Long): ViewModel() {

    private val _schedules = MutableLiveData<List<Schedule>>()
    val schedules: LiveData<List<Schedule>>
        get() = _schedules

    private val _way = MutableLiveData<Char>()
    val way: LiveData<Char>
        get() = _way

    private val _favButton = MutableLiveData<String>()
    val favButton: LiveData<String>
        get() = _favButton


    // coroutine
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        _way.value = 'A'
        _favButton.value = "Add to fav"
        getStation()
        getSchedules()
    }

    // network
    fun getSchedules() {
        coroutineScope.launch {
            val getStationDeferred = RatpApi.retrofitService.getStationAsync(type, code, station.slug, _way.value)

            try {
                val resStation = getStationDeferred.await()
                _schedules.value = resStation.result.schedules
            } catch (e: Exception) {
                //_response.value = "Failure: ${e.message}"
            }
        }
    }

    // ui
    fun switchDirection() {
        _way.value = when (_way.value) {
            'A' -> 'R'
            else -> 'A'
        }
        getSchedules()
    }

    fun switchFavorite() {
        when (id) {
            0L -> addToFavorite()
            else -> removeFromFavorite()
        }
    }



    // database
    private fun getStation() {
        coroutineScope.launch {
            getStationFromDatabase()
            if(id != 0L) {
                _favButton.value = "Remove from fav"
            }
        }
    }

    private suspend fun getStationFromDatabase() {
        return withContext(Dispatchers.IO) {
            val res = database.getStation(code = code, type = type, slug = station.slug)
            if (res != null) {
                id = res
            }
        }
    }

    private fun addToFavorite() {
        coroutineScope.launch {
            addToFavoriteInDatabase()
            _favButton.value = "Remove from fav"
        }
    }

    private suspend fun addToFavoriteInDatabase() {
        return withContext(Dispatchers.IO) {
            id = database.insert(StationEntity(type = type, code = code, stationName = station.name, stationSlug = station.slug))
        }
    }

    private fun removeFromFavorite() {
        coroutineScope.launch {
            removeFromFavoriteInDataBase()
            id = 0L
            _favButton.value = "Add to fav"
        }
    }

    private suspend fun removeFromFavoriteInDataBase() {
        if (id != 0L) {
            return withContext(Dispatchers.IO) {
                database.deleteById(id)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
