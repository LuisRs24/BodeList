package com.example.appcuentas

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcuentas.clientes.DetalleAdapter
import com.example.appcuentas.data.Cliente
import com.example.appcuentas.data.DetalleCuenta
import com.example.appcuentas.repository.ARRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalleEstadoActivity : AppCompatActivity(), DetalleAdapter.OnDeleteClickListener {


    private lateinit var detalleAdapter: DetalleAdapter
    private lateinit var recyclerViewDetalles: RecyclerView
    private lateinit var arRepository: ARRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detallecuentas)


        arRepository = ARRepository(this)
        detalleAdapter = DetalleAdapter(this, this)



        recyclerViewDetalles = findViewById(R.id.recyclerViewClientes)
        recyclerViewDetalles.layoutManager = LinearLayoutManager(this)
        recyclerViewDetalles.adapter = detalleAdapter

        // Cargamos los detalles de la cuenta desde la base de datos
        cargarDetallesDesdeDB()
    }

    override fun onDeleteClick(detalle: DetalleCuenta, cliente: Cliente) {
        // Utilizar un CoroutineScope para realizar operaciones de E/S en un hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            // Eliminamos el detalle de la cuenta usando el repositorio
            arRepository.eliminarDetalleCuenta(detalle)
            // Tambien eliminamos el cliente asociado al detalle de cuenta
            arRepository.eliminarCliente(cliente)
            // Carga la lista de detalles del cliente
            cargarDetallesDesdeDB()
        }
    }

    private fun cargarDetallesDesdeDB() {

        CoroutineScope(Dispatchers.IO).launch {
            val detalles = arRepository.obtenerTodosLosDetalles()

            withContext(Dispatchers.Main) {
                detalleAdapter.actualizarDetalles(detalles)
            }
        }
    }
}

