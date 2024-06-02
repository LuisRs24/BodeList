package com.example.appcuentas

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class InicioActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio_activity)


        // Cuando se presione el CarView de RegistroyCuenta
        val cvRegistroyCuenta = findViewById<CardView>(R.id.card_registroycuenta)
        cvRegistroyCuenta.setOnClickListener{
            startActivity(Intent(this, ClientesActivity::class.java))
        }

        // Cuando se presione el CarView de Detalle y Estado de Cuenta
        val cvDetalleEstadoCuenta =  findViewById<CardView>(R.id.card_detalleCuenta)
        cvDetalleEstadoCuenta.setOnClickListener{
            startActivity(Intent(this, DetalleEstadoActivity::class.java))
        }

    }
}