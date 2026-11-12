package com.tiwgo.jogomobilecomsensores

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class tela_final : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_final)

        val btn_restart =  findViewById<Button>(R.id.btn_restart)
        val btn_iniciar = findViewById<Button>(R.id.btn_iniciar)

        var pontuacao_calculada = intent.getIntExtra("Total", 0)
        var pontuacao: TextView = findViewById(R.id.Pontos)
        pontuacao.text = "$pontuacao_calculada"

        btn_restart.setOnClickListener {
            val intent = Intent(this, tela_de_jogo::class.java)
            startActivity(intent)
        }
        btn_iniciar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}