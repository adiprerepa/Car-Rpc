package com.car_rpc.app

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class Accelerometer : AppCompatActivity(), SensorEventListener {
    private var manager: SensorManager? = null
    private lateinit var accelerometer: Sensor
    internal lateinit var coordView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        coordView = findViewById<View>(R.id.coordView) as TextView
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = manager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        manager!!.registerListener(this@Accelerometer, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(event: SensorEvent) {
        coordView.text = " ACCELEROMETER\n" + " X: " + event.values[0] + "\n Y: " + event.values[1] + "\n Z: " + event.values[2]
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {

    }
}