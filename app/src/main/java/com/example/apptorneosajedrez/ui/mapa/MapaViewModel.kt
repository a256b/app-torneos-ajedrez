package com.example.apptorneosajedrez.ui.mapa

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaViewModel : ViewModel() {

    fun getMarcadores(): List<MarkerOptions> {
        return listOf(
            MarkerOptions().position(LatLng(-43.2500462851782, -65.30809598796235))
                .title("Universidad Nacional de la Patagonia San Juan Bosco"),

            MarkerOptions().position(LatLng(-43.25751899283961, -65.3077773039466))
                .title("Departamento de Informática Trelew")
        )
    }

    fun getPosicionInicial(): LatLng {
        return LatLng(-43.2495668137496, -65.30772915723513)
    }

    fun getZoomInicial(): Float = 12f
}
