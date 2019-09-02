package com.car_rpc.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nextButton = findViewById<Button>(R.id.button)
        nextButton.setOnClickListener {
            val intent = Intent(this, ServerInformationActivity::class.java)
            startActivity(intent)
//            serverInformationScreenRelay()
        }
    }

    fun serverInformationScreenRelay() {
        val intent = Intent(this, ServerInformationActivity::class.java)
        startActivity(intent)
    }
}
