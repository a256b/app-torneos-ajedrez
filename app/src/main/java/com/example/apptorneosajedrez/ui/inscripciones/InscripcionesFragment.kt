package com.example.apptorneosajedrez.ui.inscripciones

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptorneosajedrez.databinding.FragmentInscripcionesBinding

class InscripcionesFragment : Fragment() {

    private var _binding: FragmentInscripcionesBinding? = null
    private val binding get() = _binding!!

    private val inscriptos = listOf(
        "Juan Pérez",
        "Ana González",
        "David Martínez",
        "Sofia García",
        "Carlos Rodríguez",
        "Lucía López",
        "Miguel Sánchez",
        "Isabel Hernández",
        "Andrés Díaz",
        "Elsa Romero"
    )

    private val viewModel: InscripcionesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInscripcionesBinding.inflate(inflater, container, false)
        val root = binding.root

        val adapter = InscripcionAdapter(inscriptos)
        binding.recyclerViewInscripciones.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
