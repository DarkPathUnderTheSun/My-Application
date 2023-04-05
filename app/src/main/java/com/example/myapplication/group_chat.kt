package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class group_chat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_chat)

        val extras = intent.extras
        var email = extras?.getString("email")


        val btnBack: Button = findViewById(R.id.button10)
        btnBack.setOnClickListener {
            val intent: Intent = Intent(this, MenuAdmin::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        fun getMessages(email: String) {
            // -----------------------------------------------------------------------------Get messages
            val queue = Volley.newRequestQueue(this)
            val url = "https://www.skyclad.xyz:8443/loadChat/?email=$email"

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val responseObject = JSONObject(response)
                    // if (responseObject["status"].toString() == "ok") {
                    //    val intent: Intent = Intent(this, MenuNullRole::class.java)
                    //    startActivity(intent)
                    //}
                    val tableLayout = findViewById<TableLayout>(R.id.messagesView)



                    val jsonArray = responseObject.getJSONArray("response")


                    tableLayout.removeViews(1, tableLayout.childCount - 1)


                    for (i in 0 until jsonArray.length()) {
                        val tableRow = TableRow(this)
                        val jsonObject = jsonArray.getJSONObject(i)
                        val sender = jsonObject.getString("sender")
                        val message = jsonObject.getString("message")

                        // Agregar campo email al buffer de fila
                        val textView1 = TextView(this)
                        textView1.text = sender
                        tableRow.addView(textView1)

                        // Agregar campo rol al buffer de fila
                        val textView2 = TextView(this)
                        textView2.text = message
                        tableRow.addView(textView2)

                        tableLayout.addView(tableRow)

                    }

                    //val textView1 = TextView(this)
                    //textView1.text = "String 1"
                    //tableRow.addView(textView1)

                    //val textView2 = TextView(this)
                    //textView2.text = "String 2"
                    //tableRow.addView(textView2)

                    //tableLayout.addView(tableRow)

                },
                {
                    // Si ocurre un error
                    Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show()
                })
            queue.add(stringRequest)


            // -----------------------------------------------------------------------------Get messages
        }


        getMessages(email.toString())


        val btnSend: Button = findViewById(R.id.send)
        btnSend.setOnClickListener {
            val queue = Volley.newRequestQueue(this)

            val message: EditText = findViewById(R.id.newMessage)
            val usableMessage = message.text.toString()

            val url = "https://www.skyclad.xyz:8443/sendMessage/?grupo=00000000&sender=$email&message=$usableMessage"
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    val responseObject = JSONObject(response)
                    Toast.makeText(baseContext, "Mensaje enviado", Toast.LENGTH_SHORT).show()
                },
                { Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show() })
            queue.add(stringRequest)


        }

        // ----------------------------------------------------------------- Repeat every 10 seconds

        fun repeatDelayed(delay: Long, todo: () -> Unit) {
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    todo()
                    handler.postDelayed(this, delay)
                }
            }, delay)
        }


        val delay = 5000L
        repeatDelayed(delay) {
            // Toast.makeText(baseContext, "Obteniendo mensajes...", Toast.LENGTH_SHORT).show()
            getMessages(email.toString())
        }

        // ----------------------------------------------------------------- Repeat every 10 seconds
    }
}