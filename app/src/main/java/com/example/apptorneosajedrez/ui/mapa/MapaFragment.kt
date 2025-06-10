package com.example.apptorneosajedrez.ui.mapa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.model.Marcador
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapaFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val viewModel: MapaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_mapa, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inicializarMapa()
        observarMarcadores()
    }

    private fun inicializarMapa() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.nav_mapa) as? SupportMapFragment
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

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }
}