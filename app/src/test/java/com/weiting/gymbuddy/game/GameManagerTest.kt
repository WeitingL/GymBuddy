package com.weiting.gymbuddy.game

import org.junit.Assert.assertEquals
import org.junit.Test

class GameManagerTest {

    @Test
    fun `progress increases with positive deltaY`() {
        val gameManager = GameManager()
        gameManager.onSensorData(0f, 0f, 0f)
        gameManager.onSensorData(0f, 10f, 0f) // deltaY = 10
        assertEquals(0.1f, gameManager.progress, 0.001f)
    }

    @Test
    fun `progress decreases with negative deltaY`() {
        val gameManager = GameManager()
        // Set initial progress to 0.5
        gameManager.onSensorData(0f, 0f, 0f)
        gameManager.onSensorData(0f, 50f, 0f)
        assertEquals(0.5f, gameManager.progress, 0.001f)

        // Now decrease the progress
        gameManager.onSensorData(0f, 40f, 0f) // deltaY = -10
        assertEquals(0.4f, gameManager.progress, 0.001f)
    }

    @Test
    fun `progress is clamped between 0 and 1`() {
        val gameManager = GameManager()
        // Test upper bound
        gameManager.onSensorData(0f, 0f, 0f)
        gameManager.onSensorData(0f, 200f, 0f) // progress would be 2.0 without clamping
        assertEquals(1.0f, gameManager.progress, 0.001f)

        // Test lower bound
        gameManager.onSensorData(0f, -200f, 0f) // progress would be -2.0 without clamping
        assertEquals(0.0f, gameManager.progress, 0.001f)
    }
}
