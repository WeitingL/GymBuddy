package com.weiting.gymbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.weiting.gymbuddy.game.GameManager
import com.weiting.gymbuddy.game.SensorHelper
import com.weiting.gymbuddy.ui.theme.GymBuddyTheme

class MainActivity : ComponentActivity() {

    private lateinit var sensorHelper: SensorHelper
    private val gameManager = GameManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorHelper = SensorHelper(this)
        sensorHelper.setListener(gameManager)
        enableEdgeToEdge()
        setContent {
            GymBuddyTheme {
                GameScreen(gameManager)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorHelper.start()
    }

    override fun onPause() {
        super.onPause()
        sensorHelper.stop()
    }
}

@Composable
fun GameScreen(gameManager: GameManager, modifier: Modifier = Modifier) {
    Text(
        text = "Curl count: ${gameManager.curlCount}",
        modifier = modifier
    )
}