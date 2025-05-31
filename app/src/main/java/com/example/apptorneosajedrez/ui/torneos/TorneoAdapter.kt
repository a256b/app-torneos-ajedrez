package com.example.apptorneosajedrez.ui.torneos

import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.Toast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptorneosajedrez.R

const val PREF_NAME = "torneos_prefs"
const val KEY_TORNEO_DESTACADO = "torneos_destacados"


class TorneoAdapter(
    private val torneos: List<String>,
    private val context: Context,
    private val onTorneoClick: (String) -> Unit
) : RecyclerView.Adapter<TorneoAdapter.TorneoViewHolder>() {


    inner class TorneoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textNombreTorneo)
        private val estrellaImageView: ImageView = itemView.findViewById(R.id.imgEstrella)
        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        fun bind(nombreTorneo: String) {
            nombreTextView.text = nombreTorneo
            itemView.setOnClickListener {
                onTorneoClick(nombreTorneo)
            }

            val favoritos =
                sharedPreferences.getStringSet(KEY_TORNEO_DESTACADO, setOf())?.toMutableSet()
                    ?: mutableSetOf()
            val esFavorito = favoritos.contains(nombreTorneo)

            estrellaImageView.setImageResource(
                if (esFavorito) R.drawable.ic_star2 else R.drawable.ic_star
            )

            estrellaImageView.setOnClickListener {
                val nuevosFavoritos =
                    sharedPreferences.getStringSet(KEY_TORNEO_DESTACADO, setOf())?.toMutableSet()
                        ?: mutableSetOf()

                if (nuevosFavoritos.contains(nombreTorneo)) {
                    nuevosFavoritos.remove(nombreTorneo)
                    Toast.makeText(context, "Torneo desmarcado", Toast.LENGTH_SHORT).show()
                } else {
                    nuevosFavoritos.add(nombreTorneo)
                    Toast.makeText(context, "★ Torneo destacado", Toast.LENGTH_SHORT).show()
                }

                sharedPreferences.edit()
                    .putStringSet(KEY_TORNEO_DESTACADO, nuevosFavoritos)
                    .apply()

                notifyItemChanged(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TorneoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_torneo, parent, false)
        return TorneoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TorneoViewHolder, position: Int) {
        holder.bind(torneos[position])
    }

    override fun getItemCount(): Int = torneos.size
}