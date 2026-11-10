package com.tiwgo.jogomobilecomsensores

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class tela_de_jogo : AppCompatActivity() {
    lateinit var playerManager: PlayerManager
    lateinit var obstacleSpawner: ObstacleSpawner
    lateinit var gameLoop: GameLoop
    lateinit var gameTimer: GameTimer
    lateinit var cronometro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_de_jogo)

        cronometro = findViewById<TextView>(R.id.cronometro)

        gameTimer = GameTimer()
        gameLoop = GameLoop{
            deltaTime -> update(deltaTime)
        }

        gameLoop.start()
        gameTimer.start()
    }

    fun update(deltaTime: Float){
        gameTimer.getElapsedTimeMillis()
        cronometro.setText(gameTimer.getFormattedTime())

        for (obstacle in obstacleSpawner.obstacles) {
            val collided = CollisionDetector.checkCollision(
                playerX = playerManager.x,
                playerY = playerManager.y,
                playerW = playerManager.spriteWidth,
                playerH = playerManager.spriteHeight,
                obstacleX = obstacle.x,
                obstacleY = obstacle.y,
                obstacleW = obstacle.width,
                obstacleH = obstacle.height
            )

            if (collided) {
                playerManager.spawnAtStartLine()
                break
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}