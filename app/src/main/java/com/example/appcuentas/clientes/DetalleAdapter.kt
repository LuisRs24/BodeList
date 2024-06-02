package com.example.appcuentas.clientes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcuentas.R
import com.example.appcuentas.data.Cliente
import com.example.appcuentas.data.DetalleCuenta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DetalleAdapter(private val context: Context, private val onDeleteClickListener: OnDeleteClickListener) : RecyclerView.Adapter<DetalleAdapter.DetalleViewHolder>() {

    private var detalles: List<DetalleCuenta> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itemdetallecuentas_activity, parent, false)
        return DetalleViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetalleViewHolder, position: Int) {
        val detalle = detalles[position]
        holder.bind(detalle)
    }

    override fun getItemCount(): Int {
        return detalles.size
    }


    fun actualizarDetalles(nuevosDetalles: List<DetalleCuenta>) {
        detalles = nuevosDetalles
        notifyDataSetChanged()
    }

    inner class DetalleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewNombre: TextView = itemView.findViewById(R.id.itemDetCuenteNombre)
        private val textViewTotal: TextView = itemView.findViewById(R.id.itemPedidoTotal)
        private val textViewFecha: TextView = itemView.findViewById(R.id.itemPedidoFecha)
        private val textViewEstado: TextView = itemView.findViewById(R.id.itemPedidoEstado)
        private val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminarCuenta)

        init {
            btnEliminar.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val detalle = detalles[position]
                    val cliente = detalle.cliente // Permite obtener el cliente asociado al detalle de cuenta
                    onDeleteClickListener.onDeleteClick(detalle, cliente)
                }
            }
        }

        fun bind(detalle: DetalleCuenta) {
            textViewNombre.text = detalle.cliente.nombreCliente
            textViewTotal.text = "${detalle.montoTotal}"

            val formatedDate = formatearFecha(detalle.fechaRegistroDetalle)
            textViewFecha.text = "$formatedDate"
            textViewEstado.text = "${if (detalle.estadoDetalleCuenta) "Abierto" else "Cerrado"}"
        }
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(detalle: DetalleCuenta, cliente: Cliente)
    }

    private fun formatearFecha(fechaMillis: Long): String {
        val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val fecha =
            Instant.ofEpochMilli(fechaMillis).atZone(ZoneId.systemDefault()).toLocalDateTime()
        return fecha.format(formato)
    }


}