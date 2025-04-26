package com.example.apptorneosajedrez.ui.inscripciones

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.databinding.FragmentInscripcionesBinding
import com.example.apptorneosajedrez.databinding.FragmentTorneosBinding

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

    companion object {
        fun newInstance() = InscripcionesFragment()
    }

    private val viewModel: InscripcionesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInscripcionesBinding.inflate(inflater, container, false)
        val root = binding.root

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, inscriptos )
        binding.listViewInscripciones.adapter = adapter

        return root
    }
}