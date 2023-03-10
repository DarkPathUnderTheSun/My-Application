package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var ButtonIdioma: Button
    lateinit var btn: Button
    lateinit var btnInicioSesion: Button
    @SuppressLint("ResourceType", "MissingInflatedId")
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

        val BIdioma: Button = findViewById(R.id.cIdioma)
        BIdioma.setOnClickListener{
            if (BIdioma.isPressed) {
                actualizarResource("en")
            }
            else {
                actualizarResource("es")
            }
        }


        }

    fun actualizarResource(idioma: String){
        val recursos = resources
        val displayMetrics = recursos.displayMetrics
        val configuracion = resources.configuration
        configuracion.setLocale(Locale(idioma))
        recursos.updateConfiguration(configuracion, displayMetrics)
        configuracion.locale = Locale(idioma)
        recursos.updateConfiguration(configuracion, displayMetrics)

        ButtonIdioma.text = recursos.getString(R.string.changeLanguage)
        btnInicioSesion.text = recursos.getString(R.string.Login)
        btn.text = recursos.getString(R.string.SignUp)
            }

}





