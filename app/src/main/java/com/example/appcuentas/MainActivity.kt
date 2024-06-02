package com.example.appcuentas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val actionBar = supportActionBar
        actionBar?.hide()

        val  btnMain = findViewById<Button>(R.id.btn_main)
        btnMain.setOnClickListener{
            startActivity(Intent(this,InicioActivity::class.java))
        }
    }

}