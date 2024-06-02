package com.example.appcuentas.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.format.DateTimeFormatter

@Entity(tableName = "CLIENTE")
data class Cliente(
    @PrimaryKey(autoGenerate = true) val idCliente: Int = 0,
    val fechaRegistro: Long, // en kotlin representa el localdatetime como long
    val nombreCliente: String
)
