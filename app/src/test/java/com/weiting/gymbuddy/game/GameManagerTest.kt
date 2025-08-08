package com.weiting.gymbuddy.game

import org.junit.Assert.assertEquals
import org.junit.Test

class GameManagerTest {

    @Test
    fun `test curl counting logic`() {
        val gameManager = GameManager()

        // Simulate a bicep curl
        gameManager.onSensorData(0f, 0f, 0f) // Initial state
        gameManager.onSensorData(0f, 6f, 0f) // Moving up
        gameManager.onSensorData(0f, 7f, 0f)
        gameManager.onSensorData(0f, 1f, 0f) // Moving down
        gameManager.onSensorData(0f, 0f, 0f)

        assertEquals(1, gameManager.curlCount)

        // Simulate another curl
        gameManager.onSensorData(0f, 6f, 0f) // Moving up
        gameManager.onSensorData(0f, 7f, 0f)
        gameManager.onSensorData(0f, 1f, 0f) // Moving down
        gameManager.onSensorData(0f, 0f, 0f)

        assertEquals(2, gameManager.curlCount)
    }
}
