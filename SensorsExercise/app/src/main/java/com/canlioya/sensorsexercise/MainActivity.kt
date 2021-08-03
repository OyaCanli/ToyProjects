package com.canlioya.sensorsexercise

import android.content.Context
import android.hardware.*
import android.hardware.SensorManager.SENSOR_STATUS_NO_CONTACT
import android.hardware.SensorManager.SENSOR_STATUS_UNRELIABLE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.canlioya.sensorsexercise.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    private lateinit var binding : ActivityMainBinding

    private var lightSensor: Sensor? = null
    private var pressureSensor: Sensor? = null
    private var proximitySensor: Sensor? = null
    private var temperatureSensor: Sensor? = null
    private var rotationSensor: Sensor? = null
    private var magneticFieldSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if(lightSensor == null) {
            binding.lightValue.text = "Light sensor not available"
        }

        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        if(pressureSensor == null) {
            binding.pressureValue.text = "Barometer not available"
        }

        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if(proximitySensor == null) {
            binding.pressureValue.text = "Proximity sensor not available"
        }

        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if(temperatureSensor == null) {
            binding.temperatureValue.text = "Temperature sensor not available"
        }

        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if(rotationSensor == null) {
            binding.rotationState.text = "Gyroscope not available"
        }

        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        if(magneticFieldSensor == null) {
            binding.metalState.text = "No magnetic field detector"
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when(event?.sensor?.type) {
            Sensor.TYPE_LIGHT -> {
                // The light sensor returns a single value.
                // Many sensors return 3 values, one for each axis.
                val lux = event.values[0]
                binding.lightValue.text = lux.toString()
            }
            Sensor.TYPE_PRESSURE -> {
                binding.pressureValue.text = event.values?.get(0)?.toString()
            }
            Sensor.TYPE_PROXIMITY -> {
                binding.proximityValue.text = event.values?.get(0)?.toString()
                if(event.values?.get(0)!! < proximitySensor?.maximumRange!!){
                    Log.d("Sensors", "There is something nearby")
                }
            }
            Sensor.TYPE_AMBIENT_TEMPERATURE -> {
                binding.temperatureValue.text = event.values?.get(0).toString()
            }
            Sensor.TYPE_GYROSCOPE -> {
                val rotationValue = event.values?.get(2)
                binding.rotationState.text = if(rotationValue!! > 0.5f) "Counter clockwise" else "Clockwise"
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                binding.metalState.text = calculateAndFormatMagneticField(event.values)
            }
            else -> throw IllegalArgumentException("You forgot to handle this sensor type.")
        }
    }

    private fun calculateAndFormatMagneticField(values: FloatArray) : String {
        val azimuth = values[0]
        val pitch = values[1]
        val roll = values[2]

        val magnitude = sqrt(azimuth.pow(2) + pitch.pow(2) + roll.pow(2))

        val dec = DecimalFormat("#,###.##")
        return dec.format(magnitude)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Do something here if sensor accuracy changes.
        if(accuracy == SENSOR_STATUS_NO_CONTACT  || accuracy == SENSOR_STATUS_UNRELIABLE) {
            val message = when(sensor?.type) {
                Sensor.TYPE_LIGHT -> "Gyroscope is not reliable"
                Sensor.TYPE_PRESSURE -> "Barometer is not reliable"
                Sensor.TYPE_PROXIMITY -> "Proximity sensor is not reliable"
                Sensor.TYPE_AMBIENT_TEMPERATURE -> "Temperature sensor is not reliable"
                Sensor.TYPE_GYROSCOPE -> "Rotation sensor is not reliable"
                Sensor.TYPE_MAGNETIC_FIELD -> "Magnetic field detector is not reliable"

                else -> throw IllegalArgumentException("You forgot to handle this sensor type.")
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        registerListenerForSensor(lightSensor)
        registerListenerForSensor(pressureSensor)
        registerListenerForSensor(proximitySensor)
        registerListenerForSensor(temperatureSensor)
        registerListenerForSensor(rotationSensor)
        registerListenerForSensor(magneticFieldSensor)
    }

    private fun registerListenerForSensor(sensor: Sensor?) {
        sensor?.let {
            sensorManager.registerListener(this@MainActivity, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this, lightSensor)
        sensorManager.unregisterListener(this, pressureSensor)
        sensorManager.unregisterListener(this, proximitySensor)
        sensorManager.unregisterListener(this, temperatureSensor)
        sensorManager.unregisterListener(this, rotationSensor)
        sensorManager.unregisterListener(this, magneticFieldSensor)
    }
}