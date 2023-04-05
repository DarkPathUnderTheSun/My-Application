package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MenuAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_admin)

        // Activity code goes here
        val extras = intent.extras
        var email = extras?.getString("email")
        // val table: TableLayout = findViewById(R.id.table1)
        // Toast.makeText(baseContext, email, Toast.LENGTH_SHORT).show()

        val queue = Volley.newRequestQueue(this)
        val url = "https://www.skyclad.xyz:8443/fetchUsersInMyGroup/?adminEmail=$email"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val responseObject = JSONObject(response)
                // if (responseObject["status"].toString() == "ok") {
                //    val intent: Intent = Intent(this, MenuNullRole::class.java)
                //    startActivity(intent)
                //}
                val tableLayout = findViewById<TableLayout>(R.id.table1)



                val jsonArray = responseObject.getJSONArray("response")

                for (i in 0 until jsonArray.length()) {
                    val tableRow = TableRow(this)
                    val jsonObject = jsonArray.getJSONObject(i)
                    val correo = jsonObject.getString("correos")
                    val rol = jsonObject.getString("roles")

                    // Agregar campo email al buffer de fila
                    val textView1 = TextView(this)
                    textView1.text = correo
                    tableRow.addView(textView1)

                    // Agregar campo rol al buffer de fila
                    val textView2 = TextView(this)
                    textView2.text = rol
                    tableRow.addView(textView2)

                    // Hacer clickable la fila
                    tableRow.isClickable = true
                    tableRow.setOnClickListener { view ->
                        // Que pasa cuando haces click en la fila
                        val tablerow = view as TableRow
                        val sample1 = tablerow.getChildAt(0) as TextView
                        val targetUser = sample1.text.toString()
                        val sample2 = tablerow.getChildAt(1) as TextView
                        val currentRole = sample2.text.toString()

                        if(currentRole == "None") {
                            val queue2 = Volley.newRequestQueue(this)
                            val url = "https://www.skyclad.xyz:8443/changeRole/?targetUser=$targetUser&newRole=clerk"
                            val stringRequest = StringRequest(
                                Request.Method.GET, url,
                                { response ->
                                    val responseObject = JSONObject(response)
                                    Toast.makeText(baseContext, responseObject["status"].toString(), Toast.LENGTH_SHORT).show()
                                },
                                { Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show() })
                            queue2.add(stringRequest)
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                        }

                        if(currentRole == "clerk") {
                            val queue2 = Volley.newRequestQueue(this)
                            val url = "https://www.skyclad.xyz:8443/changeRole/?targetUser=$targetUser&newRole=inventory"
                            val stringRequest = StringRequest(
                                Request.Method.GET, url,
                                { response ->
                                    val responseObject = JSONObject(response)
                                    Toast.makeText(baseContext, responseObject["status"].toString(), Toast.LENGTH_SHORT).show()
                                },
                                { Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show() })
                            queue2.add(stringRequest)
                            //Activity.recreate()
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                        }

                        if(currentRole == "inventory") {
                            val queue2 = Volley.newRequestQueue(this)
                            val url = "https://www.skyclad.xyz:8443/changeRole/?targetUser=$targetUser&newRole=admin"
                            val stringRequest = StringRequest(
                                Request.Method.GET, url,
                                { response ->
                                    val responseObject = JSONObject(response)
                                    Toast.makeText(baseContext, responseObject["status"].toString(), Toast.LENGTH_SHORT).show()
                                },
                                { Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show() })
                            queue2.add(stringRequest)
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                        }

                        if(currentRole == "admin") {
                            val queue2 = Volley.newRequestQueue(this)
                            val url = "https://www.skyclad.xyz:8443/changeRole/?targetUser=$targetUser&newRole=None"
                            val stringRequest = StringRequest(
                                Request.Method.GET, url,
                                { response ->
                                    val responseObject = JSONObject(response)
                                    Toast.makeText(baseContext, responseObject["status"].toString(), Toast.LENGTH_SHORT).show()
                                },
                                { Toast.makeText(baseContext, "Error en conexión", Toast.LENGTH_SHORT).show() })
                            queue2.add(stringRequest)
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                        }





                        // Toast.makeText(baseContext, result, Toast.LENGTH_SHORT).show()
                    }

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

        val btnBack: Button = findViewById(R.id.button8)
        btnBack.setOnClickListener {
            val intent: Intent = Intent(this, inicioSesion::class.java)
            startActivity(intent)
        }

        val btnChat: Button = findViewById(R.id.button9)
        btnChat.setOnClickListener {
            val intent: Intent = Intent(this, group_chat::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }


    }
}