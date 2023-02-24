package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.registrarse)

        btn.setOnClickListener {
            val intent: Intent = Intent(this, Registro:: class.java)
            startActivity(intent)
        }

        val btnInicioSesion: Button = findViewById(R.id.IniciarSesion)

        btnInicioSesion.setOnClickListener {
            val intentSesion: Intent = Intent(this, inicioSesion:: class.java)
            startActivity(intentSesion)
        }
    }
}