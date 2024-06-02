package com.example.appcuentas.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "PRODUCTOBYCLIENTE",foreignKeys = [
    ForeignKey(
        entity = Cliente::class,
        parentColumns = ["idCliente"],
        childColumns = ["idCliente"],
        onDelete = ForeignKey.CASCADE // Use onDelete directly
    )
])
data class ProductoByCliente(
    @PrimaryKey(autoGenerate = true) val idProductoByCliente: Int = 0,
    val idCliente: Int,
    val nombreProducto: String,
    val fechaRegistroProducto: Long, // En kotlin el localdatetime se representa como long
    val precio: Double
)
