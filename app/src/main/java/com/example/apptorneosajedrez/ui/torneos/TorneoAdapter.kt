package com.example.apptorneosajedrez.ui.torneos // Declaración del paquete donde se encuentra la clase

import android.view.LayoutInflater // Importa la clase para inflar layouts
import android.view.View // Importa la clase View para manejar elementos de UI
import android.view.ViewGroup // Importa ViewGroup, contenedor de vistas
import android.widget.TextView // Importa TextView para mostrar texto
import androidx.recyclerview.widget.RecyclerView // Importa RecyclerView para crear listas
import com.example.apptorneosajedrez.R // Importa la clase R que contiene referencias a recursos

class TorneoAdapter(
    private val torneos: List<String>, // Lista de nombres de torneos que se mostrarán
    private val onTorneoClick: (String) -> Unit // Función lambda que maneja el click en un torneo, recibe el nombre y no devuelve nada (Unit)
) : RecyclerView.Adapter<TorneoAdapter.TorneoViewHolder>() { // Hereda de RecyclerView.Adapter con tipo genérico TorneoViewHolder

    inner class TorneoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // Clase interna que define el ViewHolder, contiene referencia a la vista del item
        private val nombreTextView: TextView = itemView.findViewById(R.id.textNombreTorneo) // Obtiene referencia al TextView que mostrará el nombre del torneo

        fun bind(nombreTorneo: String) { // Método para vincular los datos con la vista
            nombreTextView.text = nombreTorneo // Asigna el nombre del torneo al TextView
            itemView.setOnClickListener { // Configura el listener de click para toda la vista del item
                onTorneoClick(nombreTorneo) // Cuando se hace click, ejecuta la función lambda pasando el nombre del torneo
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TorneoViewHolder { // Método llamado cuando se necesita crear un nuevo ViewHolder
        val view = LayoutInflater.from(parent.context) // Obtiene un inflador desde el contexto del parent
            .inflate(R.layout.item_torneo, parent, false) // Infla el layout item_torneo.xml (crea la vista)
        return TorneoViewHolder(view) // Devuelve un nuevo ViewHolder con la vista inflada
    }

    override fun onBindViewHolder(holder: TorneoViewHolder, position: Int) { // Método llamado para mostrar datos en la posición especificada
        holder.bind(torneos[position]) // Llama al método bind del ViewHolder pasando el nombre del torneo en la posición actual
    }

    override fun getItemCount(): Int = torneos.size // Devuelve la cantidad total de elementos en la lista
}