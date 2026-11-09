package com.tiwgo.jogomobilecomsensores

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class tela_final : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_final)

        val btn_restart =  findViewById<Button>(R.id.btn_restart)

        btn_restart.setOnClickListener {
            val intent = Intent(this, tela_de_jogo::class.java)
            startActivity(intent)
        }
    }
}