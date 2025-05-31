package com.example.apptorneosajedrez.ui.torneos // Declaración del paquete donde se encuentra la clase

import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.Toast
import android.view.LayoutInflater // Importa la clase para inflar layouts
import android.view.View // Importa la clase View para manejar elementos de UI
import android.view.ViewGroup // Importa ViewGroup, contenedor de vistas
import android.widget.TextView // Importa TextView para mostrar texto
import androidx.recyclerview.widget.RecyclerView // Importa RecyclerView para crear listas
import com.example.apptorneosajedrez.R // Importa la clase R que contiene referencias a recursos

const val PREF_NAME = "torneos_prefs"
const val KEY_TORNEO_DESTACADO = "torneos_destacados"


class TorneoAdapter(
    private val torneos: List<String>, // Lista de nombres de torneos que se mostrarán
    private val context: Context,
    private val onTorneoClick: (String) -> Unit // Función lambda que maneja el click en un torneo, recibe el nombre y no devuelve nada (Unit)
) : RecyclerView.Adapter<TorneoAdapter.TorneoViewHolder>() { // Hereda de RecyclerView.Adapter con tipo genérico TorneoViewHolder


    inner class TorneoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textNombreTorneo)
        private val estrellaImageView: ImageView = itemView.findViewById(R.id.imgEstrella)
        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        fun bind(nombreTorneo: String) {
            nombreTextView.text = nombreTorneo
            itemView.setOnClickListener { // Configura el listener de click para toda la vista del item
                onTorneoClick(nombreTorneo) // Cuando se hace click, ejecuta la función lambda pasando el nombre del torneo
            }

            val favoritos = sharedPreferences.getStringSet(KEY_TORNEO_DESTACADO, setOf())?.toMutableSet() ?: mutableSetOf()
            val esFavorito = favoritos.contains(nombreTorneo)

            estrellaImageView.setImageResource(
                if (esFavorito) R.drawable.ic_star2 else R.drawable.ic_star
            )

            estrellaImageView.setOnClickListener {
                val nuevosFavoritos = sharedPreferences.getStringSet(KEY_TORNEO_DESTACADO, setOf())?.toMutableSet() ?: mutableSetOf()

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