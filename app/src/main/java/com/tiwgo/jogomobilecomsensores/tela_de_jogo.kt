package com.tiwgo.jogomobilecomsensores

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class tela_de_jogo : AppCompatActivity() {
    lateinit var gameLoop: GameLoop
    lateinit var gameTimer: GameTimer
    lateinit var cronometro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_de_jogo)

        cronometro = findViewById<TextView>(R.id.cronometro)

        gameTimer = GameTimer()
        gameLoop = GameLoop{
            update()
        }
        gameLoop.start()
        gameTimer.start()
    }

    fun update(){
        gameTimer.getElapsedTimeMillis()
        cronometro.setText(gameTimer.getFormattedTime())

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}