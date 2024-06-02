package com.example.appcuentas.repository



import android.content.Context
import android.util.Log
import com.example.appcuentas.dao.ClienteDAO
import com.example.appcuentas.dao.DetalleCuentaDAO
import com.example.appcuentas.dao.ProductoByClienteDAO
import com.example.appcuentas.data.Cliente
import com.example.appcuentas.data.DetalleCuenta
import com.example.appcuentas.database.AppDatabase



class ARRepository (context : Context)  {

    private val appDatabase = AppDatabase.getInstance(context)
    private val clienteDAO: ClienteDAO = appDatabase.clienteDAO()
    private val detalleCuentaDAO: DetalleCuentaDAO = appDatabase.detalleCuentaDAO()
    private val productoByClienteDAO: ProductoByClienteDAO = appDatabase.productoByClienteDAO()



    fun eliminarDetalleCuenta(detalleCuenta: DetalleCuenta) {
        val clienteId = detalleCuenta.idCliente
        appDatabase.detalleCuentaDAO().eliminarDetalleCuenta(detalleCuenta)

        // elimina al cliente asociado a la id del cliente del detalle de la cuenta
        val cliente = appDatabase.clienteDAO().obtenerClientePorId(clienteId)
        cliente?.let {
            appDatabase.clienteDAO().eliminarCliente(it)
        }
    }

    fun eliminarCliente(cliente: Cliente) {
        appDatabase.clienteDAO().eliminarCliente(cliente)
    }

    fun obtenerTodosLosClientes(): List<Cliente> {
        val clientes = clienteDAO.obtenerTodosLosClientes()
        Log.d("ARRepository", "Clientes listados: $clientes")
        return clientes
    }
    fun insertarCliente(cliente: Cliente): Long {
        val clienteId = clienteDAO.insertarCliente(cliente)
        Log.d("ARRepository", "Cliente insertado con ID: $clienteId")
        return clienteId
    }

    fun insertarDetalleCuenta(detalleCuenta: DetalleCuenta): Long {
        val idDetalleCuenta = detalleCuentaDAO.insertarDetalleCuenta(detalleCuenta)
        Log.d("ARRepository", "Detalle insertado con exito. ID: $idDetalleCuenta")
        return idDetalleCuenta
    }

    fun obtenerTodosLosDetalles(): List<DetalleCuenta> {
        return detalleCuentaDAO.obtenerTodosLosDetalless()

    }








}




