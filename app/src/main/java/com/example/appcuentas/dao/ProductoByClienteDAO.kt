package com.example.appcuentas.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appcuentas.data.ProductoByCliente


@Dao
interface ProductoByClienteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductoByCliente(productoByCliente: ProductoByCliente): Long

    @Query("SELECT SUM(precio) FROM productobycliente WHERE idCliente = :clienteId")
    fun getTotalPrecioProductos(clienteId: Int): Double




}