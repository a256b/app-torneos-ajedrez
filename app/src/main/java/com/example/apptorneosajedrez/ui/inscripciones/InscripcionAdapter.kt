package com.example.apptorneosajedrez.ui.inscripciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptorneosajedrez.R

class InscripcionAdapter(private val inscriptos: List<String>) :
    RecyclerView.Adapter<InscripcionAdapter.InscripcionViewHolder>() {

    class InscripcionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textNombreInscripto)

        fun bind(nombre: String) {
            nombreTextView.text = nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InscripcionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inscripcion, parent, false)
        return InscripcionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InscripcionViewHolder, position: Int) {
        holder.bind(inscriptos[position])
    }

    override fun getItemCount(): Int = inscriptos.size
}
