package com.weiting.gymbuddy.game

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GameManager : SensorHelper.SensorListener {

    var ballPositionX by mutableStateOf(0f)
        private set

    var ballPositionY by mutableStateOf(0f)
        private set

    private var screenWidth = 0f
    private var screenHeight = 0f

    override fun onSensorData(x: Float, y: Float, z: Float) {
        // We invert the x and y values because the phone is held in landscape mode
        // when attached to the arm for bicep curls.
        // Also, the sensor's coordinate system is different from the screen's.
        // A positive x acceleration should move the ball to the right.
        // A positive y acceleration should move the ball down.
        // This mapping might need to be adjusted based on how the phone is oriented.
        ballPositionX -= x * 2.0f // Multiplier to make the movement more noticeable
        ballPositionY += y * 2.0f

        // Clamp the ball's position to the screen boundaries
        if (screenWidth > 0 && screenHeight > 0) {
            ballPositionX = ballPositionX.coerceIn(0f, screenWidth)
            ballPositionY = ballPositionY.coerceIn(0f, screenHeight)
        }
    }

    fun setScreenSize(width: Float, height: Float) {
        screenWidth = width
        screenHeight = height
    }

    fun setInitialBallPosition(x: Float, y: Float) {
        ballPositionX = x
        ballPositionY = y
    }
}
