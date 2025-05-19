package com.example.apptorneosajedrez.ui.torneos

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.databinding.FragmentJugadoresBinding
import com.example.apptorneosajedrez.databinding.FragmentTorneosBinding
import com.example.apptorneosajedrez.ui.torneos.TorneoAdapter

class TorneosFragment : Fragment() {

    private var _binding: FragmentTorneosBinding? = null
    private val binding get() = _binding!!

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
    companion object {
        fun newInstance() = TorneosFragment()
    }

    private val viewModel: TorneosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTorneosBinding.inflate(inflater, container, false)
        val root = binding.root

        val adapter = TorneoAdapter(torneos)
        binding.recyclerViewTorneos.adapter = adapter

        return root
    }
}