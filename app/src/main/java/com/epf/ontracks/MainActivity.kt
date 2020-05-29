package com.epf.ontracks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.epf.ontracks.network.LineWithTraffic
import com.epf.ontracks.network.LinesWithTraffic

class MainActivity : AppCompatActivity() {
    lateinit var metros: List<LineWithTraffic>

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        metros = LinesWithTraffic.metros
        setContentView(R.layout.activity_main)
    }
}
