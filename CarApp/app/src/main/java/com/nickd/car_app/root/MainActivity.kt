package com.nickd.car_app.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nickd.car_app.R

class MainActivity : AppCompatActivity() {

    private val left: View = findViewById(R.id.leftArr)
    private val right: View = findViewById(R.id.rightArr)
    private val up: View = findViewById(R.id.upArr)
    private val down: View = findViewById(R.id.downArr)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        left.setOnClickListener{ }
        right.setOnClickListener{ }
        up.setOnClickListener { }
        down.setOnClickListener{ }
    }
}
