package com.car_rpc.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class UnableToConnectActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.unable_to_connect_activity)
        val retryConnection: Button = findViewById(R.id.unable_to_connect_retry)
        val changeServerInfo: Button = findViewById(R.id.change_server_info_butt)
        val host: String? = intent.getStringExtra("host")
        val port: Int? = intent.getIntExtra("port", 0)
        val key: Int? = intent.getIntExtra("key", 0)
        retryConnection.setOnClickListener {
            retryConnection(host, port, key)
        }
        changeServerInfo.setOnClickListener {
            val intent = Intent(this, ServerInformationActivity::class.java).apply {
                putExtra("host", host)
                putExtra("port", port)
                putExtra("key", key)
            }
            startActivity(intent)
        }
    }

    fun retryConnection(host: String?, port: Int?, key: Int?) {
        val intent = Intent(this, ConnectActivity::class.java).apply {
            putExtra("host", host)
            putExtra("port", port)
            putExtra("key", key)
        }
        startActivity(intent)
    }
}