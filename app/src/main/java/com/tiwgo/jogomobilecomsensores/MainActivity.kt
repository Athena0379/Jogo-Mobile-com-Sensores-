package com.tiwgo.jogomobilecomsensores

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.tela_inicial)

        val btn_start =  findViewById<Button>(R.id.btn_start)

        btn_start.setOnClickListener {
            val intent = Intent(this, tela_de_jogo::class.java)
            startActivity(intent)
        }
    }
}