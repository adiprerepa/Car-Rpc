package com.example.accelerometer_rpc

import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle

import android.content.pm.ActivityInfo
import android.widget.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var manager: SensorManager? = null
    internal var accelerometer: Sensor
    internal var xView: TextView
    internal var yView: TextView
    internal var zView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        xView = findViewById<View>(R.id.yView) as TextView
        yView = findViewById<View>(R.id.yView) as TextView
        zView = findViewById<View>(R.id.zView) as TextView
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = manager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        manager!!.registerListener(this@MainActivity, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(event: SensorEvent) {
        xView.text = " X: " + event.values[0]
        yView.text = " Y: " + event.values[1]
        zView.text = " Z: " + event.values[2]
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {

    }
}

