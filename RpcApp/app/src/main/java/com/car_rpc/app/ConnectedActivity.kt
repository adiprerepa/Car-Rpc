package com.car_rpc.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class ConnectedActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_server_connected)
        val nextButton: Button? = findViewById(R.id.next_button_connected)
        nextButton?.setOnClickListener {
            val intent = Intent(this, ControllerActivity::class.java)
            startActivity(intent)
        }
    }

}