package com.tiwgo.jogomobilecomsensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kotlin.math.max
import kotlin.math.min

class PlayerManager(
    private val bounds: GameBounds,
    val spriteWidth: Float = 100f,
    val spriteHeight: Float = 100f,
    val spriteResId: Int = 0
) : SensorEventListener {

    var x: Float = 0f
    var y: Float = 0f
    private val speedMultiplier: Float = 15f

    fun spawnAtStartLine() {
        x = (bounds.screenWidth / 2f) - (spriteWidth / 2f)
        y = bounds.maxY - spriteHeight - 100f
    }

    fun updateWithAccelerometer(sensorX: Float) {
        x -= sensorX * speedMultiplier
        x = max(bounds.minX, min(x, bounds.maxX - spriteWidth))
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            updateWithAccelerometer(event.values[0])
        }
    }
}