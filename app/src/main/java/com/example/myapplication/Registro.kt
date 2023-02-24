package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnSiguiente: Button = findViewById(R.id.register)
        val btnAtras: Button = findViewById(R.id.register3)
        val mail: EditText = findViewById(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById(R.id.editTextTextPassword)
        btnSiguiente.setOnClickListener {
            val mail = mail.text.toString()
            val password = password.text.toString()

            if((mail == "") or (password=="")) {
                Toast.makeText(baseContext, "Llena ambos campos", Toast.LENGTH_SHORT).show()
            }
            else{
                val queue = Volley.newRequestQueue(this)
                val url = "https://www.skyclad.xyz:8443/signUpRequest/?correo=$mail&password=$password"
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    { response ->
                        val responseObject = JSONObject(response)
                        Toast.makeText(baseContext, responseObject["status"].toString(), Toast.LENGTH_SHORT).show()
                        val intent: Intent = Intent(this, inicioSesion::class.java)
                        startActivity(intent)
                    },
                    { Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show() })
                queue.add(stringRequest)
            }
        }
        btnAtras.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
