package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HistorialPedidos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_pedidos)

        val btn: Button = findViewById(R.id.button14)
        btn.setOnClickListener {

            val intent: Intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}