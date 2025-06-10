package com.example.apptorneosajedrez.ui.mapa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apptorneosajedrez.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapaFragment : Fragment(), OnMapReadyCallback {


    private lateinit var map: GoogleMap
    private val viewModel: MapaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapaFragment =
            childFragmentManager.findFragmentById(R.id.nav_mapa) as? SupportMapFragment
        mapaFragment?.getMapAsync(this)
        observarMarcadores()
    }

    private fun observarMarcadores() {
        viewModel.marcadores.observe(viewLifecycleOwner) { lista ->
            if (::map.isInitialized) {
                map.clear()
                lista.forEach { marcador ->
                    val posicion = LatLng(marcador.latitud, marcador.longitud)
                    map.addMarker(MarkerOptions().position(posicion).title(marcador.nombre))
                }
                if (lista.isNotEmpty()) {
                    val first = LatLng(lista[0].latitud, lista[0].longitud)
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(first, 12f))
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }
}