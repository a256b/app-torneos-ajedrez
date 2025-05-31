package com.example.apptorneosajedrez.ui.torneos

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.databinding.FragmentTorneosBinding

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
        "Torneo Abierto de Ajedrez Ciudad de Buenos Aires",
        "Memorial Miguel Najdorf",
        "Campeonato Argentino de Ajedrez",
        "Torneo Magistral de Mar del Plata",
        "Copa Independencia de Ajedrez",
        "Festival Internacional de Ajedrez de La Plata",
        "Torneo Abierto de Ajedrez Córdoba Capital",
        "Gran Prix de Ajedrez Patagonia",
        "Torneo de Ajedrez Mendoza",
        "Torneo Abierto de Ajedrez Ciudad de Buenos Aires",
        "Memorial Miguel Najdorf",
        "Campeonato Argentino de Ajedrez",
        "Torneo Magistral de Mar del Plata",
        "Copa Independencia de Ajedrez",
        "Festival Internacional de Ajedrez de La Plata",
        "Torneo Abierto de Ajedrez Córdoba Capital",
        "Gran Prix de Ajedrez Patagonia",
        "Torneo de Ajedrez Mendoza",
    )

    companion object {
        fun newInstance() = TorneosFragment()
    }

    private val viewModel: TorneosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTorneosBinding.inflate(inflater, container, false)
        val root = binding.root

        val adapter = TorneoAdapter(torneos, requireContext()) { nombreTorneo ->
            navegarADetalleTorneo(nombreTorneo)
        }
        binding.recyclerViewTorneos.adapter = adapter

        return root
    }

    private fun navegarADetalleTorneo(nombreTorneo: String) {
        val bundle = Bundle().apply {
            putString("nombreTorneo", nombreTorneo)
        }
        findNavController().navigate(
            R.id.action_nav_torneos_to_nuevoTorneoFragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
