package com.example.apptorneosajedrez.ui.mapa

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apptorneosajedrez.databinding.FragmentMapaBinding
import com.example.apptorneosajedrez.model.Marcador
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.apptorneosajedrez.R


class MapaFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapaBinding? = null
    private val binding get() = _binding!!

    private lateinit var googleMap: GoogleMap
    private val viewModel: MapaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inicializarMapa()
        observarMarcadores()
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setOnMapLongClickListener { latLng ->
            mostrarDialogoAgregarMarcador(latLng)
        }
    }

    private fun inicializarMapa() {
        val mapFragment =
            childFragmentManager.findFragmentById(binding.navMapa.id) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    private fun observarMarcadores() {
        viewModel.marcadores.observe(viewLifecycleOwner) { marcadores ->
            if (::googleMap.isInitialized) {
                mostrarMarcadoresEnMapa(marcadores)
            }
        }
    }

    private fun mostrarMarcadoresEnMapa(marcadores: List<Marcador>) {
        googleMap.clear()

        for (marcador in marcadores) {
            val posicion = LatLng(marcador.latitud, marcador.longitud)
            val options = MarkerOptions().position(posicion).title(marcador.nombre)

            googleMap.addMarker(options)
        }

        centrarCamaraEnPrimerMarcador(marcadores)
    }

    private fun centrarCamaraEnPrimerMarcador(marcadores: List<Marcador>) {
        if (marcadores.isEmpty()) return
        val primerMarcador = LatLng(marcadores[0].latitud, marcadores[0].longitud)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(primerMarcador, 12f))
    }


    private fun mostrarDialogoAgregarMarcador(latLng: LatLng) {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_agregar_marcador, null)

        val inputNombre = dialogView.findViewById<EditText>(R.id.editTextNombre)

        AlertDialog.Builder(requireContext()).setTitle("Nuevo marcador").setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val nombre = inputNombre.text.toString().trim()
                if (nombre.isNotEmpty()) {
                    guardarMarcador(nombre, latLng)
                } else {
                    Toast.makeText(
                        requireContext(), "El nombre no puede estar vacío", Toast.LENGTH_SHORT
                    ).show()
                }
            }.setNegativeButton("Cancelar", null).show()
    }


    private fun guardarMarcador(nombre: String, latLng: LatLng) {
        val marcador = Marcador(
            nombre = nombre, latitud = latLng.latitude, longitud = latLng.longitude
        )

        viewModel.agregarMarcador(marcador) { exito ->
            val mensaje = if (exito) "Marcador guardado" else "Error al guardar"
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}