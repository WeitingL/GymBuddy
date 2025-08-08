package com.weiting.gymbuddy.game

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.math.abs

class GameManager : SensorHelper.SensorListener {

    var curlCount by mutableStateOf(0)
        private set

    private var lastY: Float? = null
    private val threshold = 5.0f // This is a magic number, should be tuned
    private var isMovingUp = false

    override fun onSensorData(x: Float, y: Float, z: Float) {
        val currentY = y
        val lastY = this.lastY

        if (lastY != null) {
            val deltaY = currentY - lastY
            if (abs(deltaY) > threshold) {
                if (deltaY > 0 && !isMovingUp) {
                    // Moving up, start of a curl
                    isMovingUp = true
                } else if (deltaY < 0 && isMovingUp) {
                    // Moving down, end of a curl
                    curlCount++
                    isMovingUp = false
                }
            }
        }
        this.lastY = currentY
    }
}
