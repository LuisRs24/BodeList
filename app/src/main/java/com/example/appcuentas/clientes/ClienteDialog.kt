package com.example.appcuentas.clientes

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appcuentas.ClientesActivity
import com.example.appcuentas.R

class ClienteDialog (context: Context,
                     private val onSaveClickListener: OnSaveClickListener
) : Dialog(context) {

    private lateinit var editTextName: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.clientedialog_activity)

        editTextName = findViewById(R.id.editTextName)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val nombreCliente = editTextName.text.toString().trim()
            if (nombreCliente.isNotEmpty()) {
                onSaveClickListener.onSaveClick(nombreCliente)
                dismiss()
            } else {
                Toast.makeText(context, "Ingrese el nombre del cliente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    interface OnSaveClickListener {
        fun onSaveClick(nombreCliente: String)
    }
}



