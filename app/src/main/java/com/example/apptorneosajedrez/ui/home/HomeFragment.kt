package com.example.apptorneosajedrez.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.ui.torneos.KEY_TORNEO_DESTACADO
import com.example.apptorneosajedrez.ui.torneos.PREF_NAME

class HomeFragment : Fragment() {

    private lateinit var textViewDestacados: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Referencia al TextView nuevo que agregamos
        textViewDestacados = view.findViewById(R.id.textViewDestacados)

        // Mostrar los torneos destacados
        mostrarFavoritos()

        return view
    }

    private fun mostrarFavoritos() {
        val prefs = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val favoritos = prefs.getStringSet(KEY_TORNEO_DESTACADO, setOf()) ?: setOf()

        if (favoritos.isNotEmpty()) {
            val texto = favoritos.joinToString("\n") { "★ $it" }
            textViewDestacados.text = "Torneos destacados:\n$texto"
        } else {
            textViewDestacados.text = "No hay torneos destacados todavía"
        }
    }

    override fun onResume() {
        super.onResume()
        mostrarFavoritos()
    }
}
