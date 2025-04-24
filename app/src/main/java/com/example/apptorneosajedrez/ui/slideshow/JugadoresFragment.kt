package com.example.apptorneosajedrez.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.apptorneosajedrez.databinding.FragmentJugadoresBinding
class JugadoresFragment : Fragment() {

    private var _binding: FragmentJugadoresBinding? = null
    private val binding get() = _binding!!

    private val jugadores = listOf(
        "María García",
        "José Rodríguez",
        "Juan Martínez",
        "Ana López",
        "Luis González"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJugadoresBinding.inflate(inflater, container, false)
        val root = binding.root

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, jugadores)
        binding.listViewJugadores.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
