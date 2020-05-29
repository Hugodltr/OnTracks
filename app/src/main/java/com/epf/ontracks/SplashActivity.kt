package com.epf.ontracks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.epf.ontracks.network.LineWithTraffic
import com.epf.ontracks.network.LinesWithTraffic
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
        Log.i("Hugo", "create splash screen")

        getLines()
    }

    private fun getLines() {
        coroutineScope.launch {
            val getLinesDeferred = RatpApi.retrofitService.getLinesAsync()
            val getTrafficDeferred = RatpApi.retrofitService.getAllTrafficAsync()

            try {
                val resLines = getLinesDeferred.await()
                val resTraffic = getTrafficDeferred.await()
                val metros: ArrayList<LineWithTraffic> = ArrayList()

                resLines.result.metros.forEachIndexed { index, line ->
                    Log.i("Hugo", "add traffic with line $index")
                    if(index < resTraffic.result.metros.size) {
                        metros.add(LineWithTraffic(
                            code = line.code,
                            name = line.name,
                            directions = line.directions,
                            id = line.id,
                            slug = resTraffic.result.metros[index].slug,
                            title = resTraffic.result.metros[index].title,
                            message = resTraffic.result.metros[index].message
                        ))
                    }
                }

                LinesWithTraffic.make(metros, metros, metros, metros, metros)

                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)

                finish()
            } catch (e: Exception) {
                //_response.value = "Failure: ${e.message}"
            }
        }
    }


}
