package com.epf.ontracks.lineslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epf.ontracks.network.RatpApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinesListViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    init {
        _response.value = "Fetching"
        getLines()
    }

    private fun getLines() {
        RatpApi.retrofitService.getProperties().enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure" + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })
    }

}
