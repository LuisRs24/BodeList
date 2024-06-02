package com.example.appcuentas


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcuentas.clientes.ClienteAdapter
import com.example.appcuentas.clientes.ClienteDialog
import com.example.appcuentas.data.Cliente
import com.example.appcuentas.data.DetalleCuenta
import com.example.appcuentas.repository.ARRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ClientesActivity : AppCompatActivity() {

    private lateinit var clienteDialog: ClienteDialog
    private lateinit var arRepository: ARRepository
    private lateinit var recyclerViewClientes: RecyclerView
    private lateinit var clientesAdapter: ClienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_activity)

        arRepository = ARRepository(this)
        clienteDialog = ClienteDialog(this, object : ClienteDialog.OnSaveClickListener {
            override fun onSaveClick(nombreCliente: String) {
                CoroutineScope(Dispatchers.IO).launch {

                    // Aca insertamos al cliente en la bd
                    val cliente = Cliente(fechaRegistro = System.currentTimeMillis(), nombreCliente = nombreCliente)
                    val clienteId = arRepository.insertarCliente(cliente)

                    // Esto nos permite generar el detalle automaticamente al momento de insertar el cliente
                    val detalleCuenta = DetalleCuenta(
                        idCliente = clienteId.toInt(),
                        cliente = cliente,
                        montoTotal = 0.0,
                        fechaRegistroDetalle = System.currentTimeMillis(),
                        estadoDetalleCuenta = false
                    )

                    arRepository.insertarDetalleCuenta(detalleCuenta)

                    // Aqui cargamos los clientes desde la bd y se visualizan en el RecyclerView
                    cargarClientesDesdeDB()
                }
            }
        })

        clientesAdapter = ClienteAdapter(this)
        recyclerViewClientes = findViewById(R.id.recyclerViewClientes)
        recyclerViewClientes.adapter = clientesAdapter
        recyclerViewClientes.layoutManager = LinearLayoutManager(this)

        val btnAddClient: Button = findViewById(R.id.btnAddClient)
        btnAddClient.setOnClickListener {
            clienteDialog.show()
        }

        cargarClientesDesdeDB()
    }

    // funcion para cargar los clientes

    private fun cargarClientesDesdeDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val clientes = arRepository.obtenerTodosLosClientes()
            Log.d("ClientesActivity", "Clientes cargados: $clientes")

            withContext(Dispatchers.Main) {
                clientesAdapter.actualizarClientes(clientes)
            }
        }
    }
}
