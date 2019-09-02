package com.car_rpc.app

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.car_rpc.app.api.task.CarAcknowledgeRpcTask

class ConnectActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_server_connecting)
        val host: String? = intent.getStringExtra("host")
        val key: Int? = intent.getIntExtra("key", 0)
        val port: Int? = intent.getIntExtra("port", 0)
        sendMessage(host, port, key)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        if (progressBar.visibility == View.GONE) {
            val intent = Intent(this, ConnectedActivity::class.java)
            startActivity(intent)
        } else {
            // pass intent extras for the case of retry connection..
            val intent = Intent(this, ConnectedActivity::class.java).apply {
                putExtra("host", host)
                putExtra("key", key)
                putExtra("port", port)
            }
            startActivity(intent)
        }
    }

    fun sendMessage(host: String?, port: Int?, controllerKey: Int?) {
        CarAcknowledgeRpcTask(this).execute(host, port.toString(), controllerKey.toString())
    }
}