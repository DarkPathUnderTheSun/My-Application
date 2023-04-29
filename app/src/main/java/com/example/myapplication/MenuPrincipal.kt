package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuPrincipal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val btnInventario: Button = findViewById(R.id.button2)
        btnInventario.setOnClickListener {

            val intent: Intent = Intent(this, inventario::class.java)
            startActivity(intent)
        }

        val btnPedidos: Button = findViewById(R.id.button3)
        btnPedidos.setOnClickListener {

            val intent: Intent = Intent(this, Pedidos::class.java)
            startActivity(intent)
        }

        val btnHistorialPedidos: Button = findViewById(R.id.button5)
        btnHistorialPedidos.setOnClickListener {

            val intent: Intent = Intent(this, HistorialPedidos::class.java)
            startActivity(intent)
        }

        val btnSiguiente: Button = findViewById(R.id.button)
        btnSiguiente.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnHistorialProductos: Button = findViewById(R.id.button6)
        btnHistorialProductos.setOnClickListener {
            val intent: Intent = Intent(this, HistorialProductos::class.java)
            startActivity(intent)
        }

        val btnReportes: Button = findViewById(R.id.button7)
        btnReportes.setOnClickListener {
            val intent: Intent = Intent(this, Reportes::class.java)
            startActivity(intent)
        }

    }
}