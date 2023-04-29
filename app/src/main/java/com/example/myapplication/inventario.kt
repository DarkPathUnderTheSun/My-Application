package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class inventario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)


        val queue = Volley.newRequestQueue(this)
        val url = "https://www.skyclad.xyz:8443/loadInventario/?"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val responseObject = JSONObject(response)
                // if (responseObject["status"].toString() == "ok") {
                //    val intent: Intent = Intent(this, MenuNullRole::class.java)
                //    startActivity(intent)
                //}
                val tableLayout = findViewById<TableLayout>(R.id.tablaInventario)



                val jsonArray = responseObject.getJSONArray("response")


                tableLayout.removeViews(1, tableLayout.childCount - 1)


                for (i in 0 until jsonArray.length()) {
                    val tableRow = TableRow(this)
                    val jsonObject = jsonArray.getJSONObject(i)
                    val productID = jsonObject.getString("id")
                    val productName = jsonObject.getString("productName")
                    val productPrice = jsonObject.getString("price")
                    val productStock = jsonObject.getString("stock")


                    // Agregar campo id
                    val textView1 = TextView(this)
                    textView1.text = productID
                    tableRow.addView(textView1)

                    // Agregar campo nombre de producto
                    val textView2 = TextView(this)
                    textView2.text = productName
                    tableRow.addView(textView2)

                    // Agregar campo precio
                    val textView3 = TextView(this)
                    textView3.text = productPrice
                    tableRow.addView(textView3)

                    // Agregar campo stock
                    val textView4 = TextView(this)
                    textView4.text = productStock
                    tableRow.addView(textView4)

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

    }
}
