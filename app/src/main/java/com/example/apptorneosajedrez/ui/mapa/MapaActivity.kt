package com.example.apptorneosajedrez.ui.mapa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apptorneosajedrez.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.nav_mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Mapa"
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //TODO: No carga correctamente el mapa - problema en como lee la key desde local.properties
        map = googleMap

        Toast.makeText(this, "Entra en onMapReady", Toast.LENGTH_SHORT).show()

        //val zoom = 12f
        val trelew = LatLng(-43.2495668137496, -65.30772915723513)
        //map.addMarker(MarkerOptions().position(trelew).title("Trelew"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(trelew, 12f))

        val unpsjb = LatLng(-43.2500462851782, -65.30809598796235)
        googleMap.addMarker(MarkerOptions().position(unpsjb).title("Universidad Nacional de la Patagonia San Juan Bosco"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(unpsjb))
        val dit = LatLng(-43.25751899283961, -65.3077773039466)
        googleMap.addMarker(MarkerOptions().position(dit).title("Departamento de Informática Trelew"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(dit))
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
