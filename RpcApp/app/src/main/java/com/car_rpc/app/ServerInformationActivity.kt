package com.car_rpc.app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ServerInformationActivity : Activity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.server_info_main)
        val continueButt: Button = findViewById(R.id.continueButton)
        continueButt.setOnClickListener {
            val hostEditText: EditText? = findViewById(R.id.host_edit_text)
            val portEditText: EditText? = findViewById(R.id.portEditText)
            val controllerEditText: EditText? = findViewById(R.id.controller_key_edit_text)
            val host: String? = hostEditText!!.text.toString()
            val port: Int? = Integer.valueOf(portEditText!!.text.toString())
            val controllerKey: Int? = Integer.valueOf(controllerEditText!!.text.toString())
            tryToConnectPass(host, port, controllerKey)
        }
    }

    fun tryToConnectPass(host: String?, port: Int?, controllerKey: Int?) {
        val intent: Intent = Intent(this, ConnectActivity::class.java).apply {
            putExtra("host", host)
            putExtra("port", port)
            putExtra("key", controllerKey)
        }
        startActivity(intent)
    }

}