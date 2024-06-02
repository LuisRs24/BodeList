package com.example.appcuentas.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appcuentas.data.DetalleCuenta

@Dao
interface DetalleCuentaDAO {


    @Query("UPDATE DETALLECUENTA SET montoTotal = :montoTotal WHERE idCliente = :clienteId")
    fun updateMontoTotalProductos(clienteId: Int, montoTotal: Double)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarDetalleCuenta(detalleCuenta: DetalleCuenta) : Long

    @Delete
    fun eliminarDetalleCuenta(detalleCuenta: DetalleCuenta)

    @Query("SELECT * FROM DETALLECUENTA")
    fun obtenerTodosLosDetalless(): List<DetalleCuenta>



}