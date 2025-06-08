package com.example.apptorneosajedrez.ui.mapa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptorneosajedrez.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import androidx.fragment.app.viewModels


class MapaFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val viewModel: MapaViewModel by viewModels()

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        configurarVistaInicial()
        agregarMarcadores()
    }

    private fun configurarVistaInicial() {
        val posicionInicial = viewModel.getPosicionInicial()
        val zoom = viewModel.getZoomInicial()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(posicionInicial, zoom))
    }

    private fun agregarMarcadores() {
        viewModel.getMarcadores().forEach { marker ->
            map.addMarker(marker)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.nav_mapa) as? com.google.android.gms.maps.SupportMapFragment
        mapFragment?.getMapAsync(this)
    }
}