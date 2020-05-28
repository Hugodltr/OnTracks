package com.epf.ontracks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.epf.ontracks.network.RatpApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    // coroutine
    private var activityJob = Job()
    private val coroutineScope = CoroutineScope(activityJob + Dispatchers.Main )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getLines()
    }

    private fun getLines() {
        coroutineScope.launch {
            val getLinesDeferred = RatpApi.retrofitService.getLinesAsync()
            val getTrafficDeferred = RatpApi.retrofitService.getAllTrafficAsync()

            try {
                val resLines = getLinesDeferred.await()
                val resTraffic = getTrafficDeferred.await()

                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("LINES", resLines.result)
                startActivity(intent)

                finish()
            } catch (e: Exception) {
                //_response.value = "Failure: ${e.message}"
            }
        }
    }
}
