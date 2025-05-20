package com.example.apptorneosajedrez.ui.torneos // Declaración del paquete donde se encuentra la clase

import androidx.fragment.app.viewModels // Importa la extensión para usar ViewModels en fragmentos
import android.os.Bundle // Para manejar datos entre fragmentos y estados
import androidx.fragment.app.Fragment // Clase base para fragmentos
import android.view.LayoutInflater // Para inflar layouts
import android.view.View // Para manejar vistas
import android.view.ViewGroup // Contenedor de vistas
import android.widget.ArrayAdapter // Para adaptadores de listas básicos (aunque no se usa)
import androidx.navigation.fragment.findNavController // Para obtener el controlador de navegación en fragmentos
import com.example.apptorneosajedrez.R // Acceso a recursos
import com.example.apptorneosajedrez.databinding.FragmentJugadoresBinding // Binding para el fragmento de jugadores (aunque no se usa)
import com.example.apptorneosajedrez.databinding.FragmentTorneosBinding // Binding para el fragmento de torneos
import com.example.apptorneosajedrez.ui.torneos.TorneoAdapter // Adaptador personalizado para torneos

class TorneosFragment : Fragment() { // Definición del fragmento para la lista de torneos

    // Variable para manejar el binding (patrón View Binding)
    private var _binding: FragmentTorneosBinding? = null // Inicialmente nulo, se inicializa en onCreateView y se limpia en onDestroyView
    private val binding get() = _binding!! // Propiedad de acceso para evitar verificaciones de nulidad repetidas

    // Lista de torneos para mostrar (datos de ejemplo)
    private val torneos = listOf(
        "Torneo Abierto de Ajedrez Ciudad de Buenos Aires",
        "Memorial Miguel Najdorf",
        "Campeonato Argentino de Ajedrez",
        "Torneo Magistral de Mar del Plata",
        "Copa Independencia de Ajedrez",
        "Festival Internacional de Ajedrez de La Plata",
        "Torneo Abierto de Ajedrez Córdoba Capital",
        "Gran Prix de Ajedrez Patagonia",
        "Torneo de Ajedrez Mendoza",
        "Open de Ajedrez Rosario"
    )

    companion object { // Objeto compañero para métodos estáticos del fragmento
        fun newInstance() = TorneosFragment() // Método de fábrica para crear instancias del fragmento
    }

    // Inicialización del ViewModel usando la extensión by viewModels()
    private val viewModel: TorneosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) { // Método llamado cuando se crea el fragmento
        super.onCreate(savedInstanceState) // Llama al método onCreate de la clase padre
    }

    override fun onCreateView(
        inflater: LayoutInflater, // Inflador para crear vistas
        container: ViewGroup?, // Contenedor padre donde se agregará el fragmento
        savedInstanceState: Bundle? // Estado guardado del fragmento
    ): View { // Devuelve la vista raíz del fragmento
        _binding = FragmentTorneosBinding.inflate(inflater, container, false) // Infla el layout usando view binding
        val root = binding.root // Obtiene la vista raíz del binding

        // Crea el adaptador con la lista de torneos y un lambda para manejar clicks
        val adapter = TorneoAdapter(torneos) { nombreTorneo ->
            navegarADetalleTorneo(nombreTorneo) // Cuando se hace click, navega al detalle
        }
        binding.recyclerViewTorneos.adapter = adapter // Asigna el adaptador al RecyclerView

        return root // Devuelve la vista raíz
    }

    // Método para navegar al fragmento de detalle del torneo
    private fun navegarADetalleTorneo(nombreTorneo: String) {
        val bundle = Bundle().apply { // Crea un bundle para pasar datos
            putString("nombreTorneo", nombreTorneo) // Agrega el nombre del torneo al bundle
        }
        findNavController().navigate( // Utiliza el controlador de navegación para navegar
            R.id.action_nav_torneos_to_nuevoTorneoFragment2, // ID de la acción definida en el gráfico de navegación
            bundle // Pasa el bundle con los datos
        )
    }

    override fun onDestroyView() { // Método llamado cuando se destruye la vista del fragmento
        super.onDestroyView() // Llama al método onDestroyView de la clase padre
        _binding = null // Limpia la referencia al binding para evitar fugas de memoria
    }
}