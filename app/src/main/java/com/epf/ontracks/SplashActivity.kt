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
        supportActionBar?.hide()
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

                val metros: ArrayList<LineWithTraffic> = ArrayList()
                val rers: ArrayList<LineWithTraffic> = ArrayList()
                val tramways: ArrayList<LineWithTraffic> = ArrayList()
                val buses: ArrayList<LineWithTraffic> = ArrayList()
                val noctiliens: ArrayList<LineWithTraffic> = ArrayList()

                resLines.result.metros.forEachIndexed { index, line ->
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

                var rerC = false
                var rerE = false
                var rerD = false
                resLines.result.rers.forEach { line ->
                    if((line.code != "C" || !rerC) && (line.code != "D" || !rerD) && (line.code != "E" || !rerE))
                    resTraffic.result.rers.forEach { lineTraffic ->
                        if(lineTraffic.line == line.code) {
                            when(line.code) {
                                "C" -> rerC = true
                                "D" -> rerD = true
                                "E" -> rerE = true
                            }
                            rers.add(LineWithTraffic(
                                code = line.code,
                                name = line.name,
                                directions = line.directions,
                                id = line.id,
                                slug = lineTraffic.slug,
                                title = lineTraffic.title,
                                message = lineTraffic.message
                            ))
                        }
                    }
                }

                resLines.result.tramways.forEachIndexed { index, line ->
                    if(index < resTraffic.result.tramways.size) {
                        tramways.add(LineWithTraffic(
                            code = line.code,
                            name = line.name,
                            directions = line.directions,
                            id = line.id,
                            slug = resTraffic.result.tramways[index].slug,
                            title = resTraffic.result.tramways[index].title,
                            message = resTraffic.result.tramways[index].message
                        ))
                    }
                }

                resLines.result.buses.forEach { line ->
                    buses.add(LineWithTraffic(
                        code = line.code,
                        name = line.name,
                        directions = line.directions,
                        id = line.id,
                        slug = "",
                        title = "",
                        message = ""
                    ))
                }

                resLines.result.noctiliens.forEach { line ->
                    noctiliens.add(LineWithTraffic(
                        code = line.code,
                        name = line.name,
                        directions = line.directions,
                        id = line.id,
                        slug = "",
                        title = "",
                        message = ""
                    ))
                }

                LinesWithTraffic.make(metros, rers, tramways, buses, noctiliens)

                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)

                finish()
            } catch (e: Exception) {
                Log.i("Hugo", "Failure : $e")
            }
        }
    }


}
