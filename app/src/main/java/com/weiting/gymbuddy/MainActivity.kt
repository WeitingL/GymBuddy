package com.weiting.gymbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
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
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    LaunchedEffect(Unit) {
        gameManager.setScreenSize(screenWidthPx, screenHeightPx)
        gameManager.setInitialBallPosition(screenWidthPx * 0.1f, screenHeightPx * 0.2f)
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val path = Path()
        path.moveTo(size.width * 0.1f, size.height * 0.2f)
        path.cubicTo(
            size.width * 0.9f, size.height * 0.4f,
            size.width * 0.1f, size.height * 0.6f,
            size.width * 0.9f, size.height * 0.8f
        )
        drawPath(
            path = path,
            color = Color.Gray,
            style = Stroke(width = 20f)
        )

        drawCircle(
            color = Color.Red,
            radius = 50f,
            center = Offset(gameManager.ballPositionX, gameManager.ballPositionY)
        )
    }
}