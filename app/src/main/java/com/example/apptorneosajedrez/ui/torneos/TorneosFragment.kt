package com.example.apptorneosajedrez.ui.torneos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.databinding.FragmentTorneosBinding

class TorneosFragment : Fragment() {

    private var _binding: FragmentTorneosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TorneoViewModel by viewModels()
    private var torneosList: List<String> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTorneosBinding.inflate(inflater, container, false)

        viewModel.torneos.observe(viewLifecycleOwner, { lista ->
            torneosList = lista.map { it.nombre }
            setupRecyclerView()
        })

        viewModel.cargarTorneos()
        return binding.root
    }

    private fun setupRecyclerView() {
        val adapter = TorneoAdapter(torneosList, requireContext()) { nombreTorneo ->
            navegarADetalleTorneo(nombreTorneo)
        }
        binding.recyclerViewTorneos.adapter = adapter
    }

    private fun navegarADetalleTorneo(nombreTorneo: String) {
        val args = Bundle().apply {
            putString("nombreTorneo", nombreTorneo)
        }
        findNavController().navigate(
            R.id.action_nav_torneos_to_nuevoTorneoFragment, args
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
