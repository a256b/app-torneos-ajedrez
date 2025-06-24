package com.example.apptorneosajedrez.ui.jugadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.model.Jugador

class JugadorAdapter(
    private var jugadores: List<Jugador>,
    private val onAceptarClick: (Jugador) -> Unit,
    private val onRechazarClick: (Jugador) -> Unit
) : RecyclerView.Adapter<JugadorAdapter.JugadorViewHolder>() {

    inner class JugadorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.textNombreJugador)
        private val btnAceptar: Button = itemView.findViewById(R.id.btnAceptar)
        private val btnRechazar: Button = itemView.findViewById(R.id.btnRechazar)

        fun bind(jugador: Jugador) {
            nombreTextView.text = jugador.nombre

            btnAceptar.setOnClickListener {
                onAceptarClick(jugador)
            }

            btnRechazar.setOnClickListener {
                onRechazarClick(jugador)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jugador, parent, false)
        return JugadorViewHolder(view)
    }

    override fun onBindViewHolder(holder: JugadorViewHolder, position: Int) {
        holder.bind(jugadores[position])
    }

    override fun getItemCount(): Int = jugadores.size

    // Opcional: para que puedas actualizar dinámicamente los datos
    fun actualizarLista(nuevaLista: List<Jugador>) {
        jugadores = nuevaLista
        notifyDataSetChanged()
    }
}
