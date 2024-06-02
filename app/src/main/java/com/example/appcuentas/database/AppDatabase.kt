package com.example.appcuentas.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.appcuentas.dao.ClienteDAO
import com.example.appcuentas.dao.DetalleCuentaDAO
import com.example.appcuentas.dao.ProductoByClienteDAO
import com.example.appcuentas.data.Cliente
import com.example.appcuentas.data.DetalleCuenta
import com.example.appcuentas.data.ProductoByCliente


@Database(entities = [Cliente::class, DetalleCuenta::class, ProductoByCliente::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun clienteDAO(): ClienteDAO
    abstract fun detalleCuentaDAO(): DetalleCuentaDAO
    abstract fun productoByClienteDAO(): ProductoByClienteDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "Adquisiregistro"
                    ).build()
                }
                return INSTANCE as AppDatabase
            }
        }
    }

}