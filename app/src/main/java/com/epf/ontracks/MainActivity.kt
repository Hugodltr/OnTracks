package com.epf.ontracks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.epf.ontracks.lineslist.Lines

class MainActivity : AppCompatActivity() {

    var lines: Lines? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        lines = intent.getParcelableExtra("LINES")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
