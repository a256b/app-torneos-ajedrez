package com.example.apptorneosajedrez.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptorneosajedrez.databinding.FragmentHomeBinding
import com.example.apptorneosajedrez.ui.torneos.KEY_TORNEO_DESTACADO
import com.example.apptorneosajedrez.ui.torneos.PREF_NAME

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root

        mostrarFavoritos()

        return root
    }

    private fun mostrarFavoritos() {
        val prefs = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val favoritos = prefs.getStringSet(KEY_TORNEO_DESTACADO, emptySet())?.toMutableSet() ?: mutableSetOf()

        val adapter = DestacadosAdapter(favoritos.toList()) { torneo ->
            favoritos.remove(torneo)
            prefs.edit().putStringSet(KEY_TORNEO_DESTACADO, favoritos).apply()
            mostrarFavoritos()
        }

        binding.recyclerViewDestacados.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDestacados.adapter = adapter
    }


    override fun onResume() {
        super.onResume()
        mostrarFavoritos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
