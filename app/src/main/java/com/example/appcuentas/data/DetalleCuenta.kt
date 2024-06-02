package com.example.appcuentas.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "DETALLECUENTA",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["idCliente"],
            childColumns = ["idCliente"],
            onDelete = ForeignKey.CASCADE //
        )
    ])
data class DetalleCuenta(
    @PrimaryKey(autoGenerate = true) val idDetalleCuenta: Int = 0,
    var idCliente: Int,
    @Embedded(prefix = "cliente_") val cliente: Cliente,
    val montoTotal: Double,
    val fechaRegistroDetalle : Long,
    val estadoDetalleCuenta: Boolean

)
