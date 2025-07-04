package com.example.apptorneosajedrez.ui.torneos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptorneosajedrez.data.InscripcionRepository
import com.example.apptorneosajedrez.databinding.FragmentTorneoDetalleBinding
import com.example.apptorneosajedrez.model.Torneo
import com.example.apptorneosajedrez.ui.movimientos.MovesActivity

class TorneoDetalleFragment : Fragment() {

    private var _binding: FragmentTorneoDetalleBinding? = null
    private val binding get() = _binding!!
    private val repoInscripciones = InscripcionRepository()

    private var torneo: Torneo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        torneo = arguments?.getSerializable("torneo") as? Torneo
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTorneoDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        torneo?.let { t ->
            binding.nombreTorneo.text = t.nombre
            binding.tvFechaInicio.text = "Fecha de inicio: ${t.fechaInicio}"
            binding.tvFechaFin.text = "Fecha de fin: ${t.fechaFin}"
            binding.tvHoraInicio.text = "Hora de inicio: ${t.horaInicio}"
            binding.tvUbicacion.text = "Lugar: ${t.ubicacion}"
            binding.tvDescripcion.text = t.descripcion

            // Cargar cantidad de inscriptos
            repoInscripciones.escucharInscripciones { inscripciones ->
                val cantidad = inscripciones.count { it.idTorneo == t.idTorneo.toString() }
                binding.tvCantidadInscriptos.text = "Inscriptos: $cantidad"
            }
        }

        binding.btnVerPartidas.setOnClickListener {
            val intent = Intent(requireContext(), MovesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}