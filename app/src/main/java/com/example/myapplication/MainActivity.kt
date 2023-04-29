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
import com.zeugmasolutions.localehelper.Locales
import java.util.*


class MainActivity : BaseActivity() {
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
           updateLocale(Locales.Spanish)
        }
        val BIdioma2: Button = findViewById(R.id.cIdioma2)
        BIdioma2.setOnClickListener{
            updateLocale(Locales.English)
        }

        }


}





