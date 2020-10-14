package com.example.dasarkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variabel : menyimpan informasi/ nilai
        //keyword : var/ val, identifier, type, dan initialization
        //Struktur : var identifier: Type = initialization
        //type : char, string, array

        var buah : String = "Apel"
        buah = "Jeruk"
        //println(buah)

        //array : menampung banyak nilai -> arrayOf
        var array = arrayOf(1, 1.2, true, 'A')
        //println(array[3])

        var bulat = intArrayOf(1, 2, 3) //doubleArrayOf, charArrayOf

        //nullable type
        var hewan: String? = null

        //println(buah?.length)
        //println(hewan?.length)

        //function : prosedur yang berisikan blok instruksi/ nilai
        //return : nilai
        fun getBio(nama: String, umur:Int): String{
            return "Saya $nama berumur $umur tahun"
        }

        //println(getBio("Nola", 21))

        //Intent : cara berpindah activity
        //action : klik button login -> MainActivity2

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if(isRemembered){
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        button_login.setOnClickListener {
            val name = editTextUsername.text.toString()
            val checked = checkBox.isChecked
            val intent = Intent(this, MainActivity2::class.java)

            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("Name", name)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()
            //intent.putExtra("Name", name)
            startActivity(intent)
        }

        var branch : String = "Berhasil"
    }
}