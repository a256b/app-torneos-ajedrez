package com.example.apptorneosajedrez.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.model.Torneo

class DestacadosAdapter(
    private val torneos: List<Torneo>,
    private val onEliminarFavorito: (Torneo) -> Unit,
    private val onTorneoClick: (Torneo) -> Unit
) : RecyclerView.Adapter<DestacadosAdapter.DestacadoViewHolder>() {

    inner class DestacadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textNombreTorneo)
        private val estrellaImageView: ImageView = itemView.findViewById(R.id.imgEstrella)

        fun bind(torneo: Torneo) {
            nombreTextView.text = torneo.nombre
            estrellaImageView.setImageResource(R.drawable.ic_star2)

            estrellaImageView.setOnClickListener {
                onEliminarFavorito(torneo)
            }

            itemView.setOnClickListener {
                onTorneoClick(torneo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestacadoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_torneo, parent, false)
        return DestacadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestacadoViewHolder, position: Int) {
        holder.bind(torneos[position])
    }

    override fun getItemCount(): Int = torneos.size
}
