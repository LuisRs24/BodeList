package com.example.appcuentas.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appcuentas.data.Cliente

@Dao
interface ClienteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  insertarCliente(cliente: Cliente): Long

    @Query("SELECT * FROM CLIENTE ORDER BY idCliente ASC")
    fun obtenerTodosLosClientes(): List<Cliente>

    @Delete
    fun eliminarCliente(cliente: Cliente)

    @Query("SELECT * FROM cliente WHERE idCliente = :clienteId LIMIT 1")
    fun obtenerClientePorId(clienteId: Int): Cliente?
}