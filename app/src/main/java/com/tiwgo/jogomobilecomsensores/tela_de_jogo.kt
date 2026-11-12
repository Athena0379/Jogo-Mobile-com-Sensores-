package com.tiwgo.jogomobilecomsensores

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class tela_de_jogo : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    var accelerometer: Sensor? = null

    lateinit var player: Player
    lateinit var gameBounds: GameBounds
    lateinit var obstacleSpawner: ObstacleSpawner
    lateinit var gameLoop: GameLoop
    lateinit var gameTimer: GameTimer
    lateinit var cronometro: TextView
    lateinit var screenSize: Vector2
    lateinit var goalZone: GameRect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_de_jogo)


        val displayMetrics = resources.displayMetrics
        screenSize = Vector2(
            x = displayMetrics.widthPixels.toFloat(),
            y = displayMetrics.heightPixels.toFloat()
        )
        gameBounds = GameBounds(screenSize, margin = 50f)

        goalZone = GameRect(
            position = Vector2(0f, 0f),
            size = Vector2(screenSize.x * 2, 200f)
        )

        obstacleSpawner = ObstacleSpawner(this, gameBounds, 10)
        obstacleSpawner.generateRandomObstacles()

        val rootView = window.decorView.findViewById<android.view.ViewGroup>(android.R.id.content)
        for (obstacle in obstacleSpawner.obstacles) {
            rootView.addView(obstacle.imageView)
        }

        player = Player(this, R.drawable.drone_topdown, customWidth = 150f, customHeight = 200f)
        player.spawnAtStartLine(gameBounds)

        player.imageView.x = player.rect.left
        player.imageView.y = player.rect.top

        rootView.addView(player.imageView)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        cronometro = findViewById<TextView>(R.id.cronometro)

        gameTimer = GameTimer()
        gameLoop = GameLoop { deltaTime ->
            update(deltaTime)
        }

        gameLoop.start()
        gameTimer.start()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val acceleration = Vector2(
                x = event.values[0],
                y = event.values[1]
            )
            player.updateWithAccelerometer(acceleration, gameBounds)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    fun update(deltaTime: Float) {
        gameTimer.getElapsedTimeMillis()
        cronometro.setText(gameTimer.getFormattedTime())

        for (obstacle in obstacleSpawner.obstacles) {
            val collided = CollisionDetector.checkCollision(
                player = player.rect,
                obstacle = obstacle.rect
            )

            if (collided) {
                player.spawnAtStartLine(gameBounds)
                player.imageView.x = player.rect.left
                player.imageView.y = player.rect.top
                break
            }
        }
        val reachedGoal = CollisionDetector.checkCollision(
            player = player.rect,
            obstacle = goalZone
        )

        if (reachedGoal) {
            var pontos = gameTimer.calculateScore()
            var intent = Intent(this, tela_final::class.java)
            intent.putExtra("Total", pontos)
            startActivity(intent)

            gameLoop.stop()
        }
    }
}