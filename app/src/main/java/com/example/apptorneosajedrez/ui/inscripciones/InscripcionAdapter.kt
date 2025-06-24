package com.example.apptorneosajedrez.ui.inscripciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptorneosajedrez.R

data class InscripcionInfo(
    val nombreJugador: String,
    val nombreTorneo: String
)

class InscripcionAdapter(private val inscripciones: List<InscripcionInfo>) :
    RecyclerView.Adapter<InscripcionAdapter.InscripcionViewHolder>() {

    class InscripcionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreJugadorTextView: TextView = itemView.findViewById(R.id.textNombreInscripto)
        private val nombreTorneoTextView: TextView = itemView.findViewById(R.id.textNombreTorneo)

        fun bind(info: InscripcionInfo) {
            nombreJugadorTextView.text = info.nombreJugador
            nombreTorneoTextView.text = info.nombreTorneo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InscripcionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inscripcion, parent, false)
        return InscripcionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InscripcionViewHolder, position: Int) {
        holder.bind(inscripciones[position])
    }

    override fun getItemCount(): Int = inscripciones.size
}
