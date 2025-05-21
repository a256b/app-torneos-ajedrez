package com.example.apptorneosajedrez.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptorneosajedrez.R

class DestacadosAdapter(
    private val torneos: List<String>,
    private val onEliminarFavorito: (String) -> Unit
) : RecyclerView.Adapter<DestacadosAdapter.DestacadoViewHolder>() {

    inner class DestacadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textNombreTorneo)
        private val estrellaImageView: ImageView = itemView.findViewById(R.id.imgEstrella)

        fun bind(nombreTorneo: String) {
            nombreTextView.text = nombreTorneo
            estrellaImageView.setImageResource(R.drawable.ic_star2)

            // Para sacar de favoritos
            estrellaImageView.setOnClickListener {
                onEliminarFavorito(nombreTorneo)
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
