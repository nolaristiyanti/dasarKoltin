package com.example.dasarkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val name = preferences.getString("Name", "")

        //val name = intent.getStringExtra("Name")
        val result = findViewById<TextView>(R.id.textView)
        result.text = "Welcome " + name

        textLogout.setOnClickListener {
            val editor : SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var db = DatabaseHandler(this)
        button_insert.setOnClickListener {
            var produk = Produk(editName.text.toString(), editStok.text.toString().toInt())
            db.insertData(produk)
        }

        button_read.setOnClickListener {
            var data = db.readData()
            textResult.text = ""
            for(i in 0..(data.size-1)){
                textResult.append(data.get(i).id.toString() + " " + data.get(i).nama +
                " " + data.get(i).stok + "\n")
            }
        }

        button_delete.setOnClickListener {
            db.deleteData()
            button_read.performClick()
        }

        button_update.setOnClickListener {
            db.updateData()
            button_read.performClick()
        }
    }
}