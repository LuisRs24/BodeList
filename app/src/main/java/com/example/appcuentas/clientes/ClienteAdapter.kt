package com.example.appcuentas.clientes

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcuentas.data.Cliente
import com.example.appcuentas.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class ClienteAdapter (private val context: Context) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    private var clientes: List<Cliente> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.bind(cliente)
    }

    override fun getItemCount(): Int {
        return clientes.size
    }

    fun actualizarClientes(nuevosClientes: List<Cliente>) {
        clientes = nuevosClientes
        notifyDataSetChanged()
    }

    inner class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewNombre: TextView = itemView.findViewById(R.id.txtnombrecliente_item)
        private val textViewIdCliente: TextView = itemView.findViewById(R.id.idcliente)
        private val textViewFechaRegistro: TextView = itemView.findViewById(R.id.fecharegis)

        fun bind(cliente: Cliente) {
            textViewNombre.text = cliente.nombreCliente
            textViewIdCliente.text = "Id Cliente: ${cliente.idCliente}"

            val formattedDate = formatearFecha(cliente.fechaRegistro)
            textViewFechaRegistro.text = "Fecha Registro: $formattedDate"
        }

    }

    private fun formatearFecha(fechaMillis: Long): String {
        val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val fecha =
            Instant.ofEpochMilli(fechaMillis).atZone(ZoneId.systemDefault()).toLocalDateTime()
        return fecha.format(formato)
    }
}