package com.weiting.gymbuddy.game

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GameManager : SensorHelper.SensorListener {

    var progress by mutableStateOf(0f)
        private set

    private var lastY: Float? = null

    override fun onSensorData(x: Float, y: Float, z: Float) {
        val currentY = y
        val lastY = this.lastY

        if (lastY != null) {
            val deltaY = currentY - lastY
            // The sensitivity of the movement can be adjusted by changing the multiplier.
            progress += deltaY * 0.01f
            progress = progress.coerceIn(0f, 1f)
        }
        this.lastY = currentY
    }
}
