package com.example.apptorneosajedrez.ui.mapa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptorneosajedrez.data.MarcadorRepository
import com.example.apptorneosajedrez.model.Marcador
import kotlinx.coroutines.launch


class MapaViewModel : ViewModel() {

    private val repositorio = MarcadorRepository()

    private val _marcadores = MutableLiveData<List<Marcador>>()
    val marcadores: LiveData<List<Marcador>> get() = _marcadores

    init {
        cargarMarcadores()
    }

    private fun cargarMarcadores() {
        viewModelScope.launch {
            _marcadores.value = repositorio.obtenerMarcadores()
        }
    }
}



