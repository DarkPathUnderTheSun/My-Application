package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class inicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)



        val btnSiguiente: Button = findViewById(R.id.buttonNext2)
        val mail: EditText = findViewById(R.id.editEmailAddress2)
        val password: EditText = findViewById(R.id.editPassword2)
        val btnAtras: Button = findViewById(R.id.buttonNext3)

        btnSiguiente.setOnClickListener {
            val mail = mail.text.toString()
            val password = password.text.toString()


            // Web Request Simple
            val queue = Volley.newRequestQueue(this)
            val url = "https://www.skyclad.xyz:8443/loginRequest/?correo=$mail&password=$password"
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    val responseObject = JSONObject(response)
                    Toast.makeText(baseContext, responseObject["status"].toString(), Toast.LENGTH_SHORT).show()
                    if (responseObject["status"].toString() == "ok") {
                        val intent: Intent = Intent(this, MenuNullRole::class.java)
                        startActivity(intent)
                    }
                    if (responseObject["status"].toString() == "admin") {
                        val intent: Intent = Intent(this, MenuAdmin::class.java)
                        intent.putExtra("email",mail)
                        startActivity(intent)
                    }
                    if (responseObject["status"].toString() == "clerk") {
                        val intent: Intent = Intent(this, MenuPrincipal::class.java)
                        startActivity(intent)
                    }
                },
                { Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show() })
            queue.add(stringRequest)
        }

        btnAtras.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}