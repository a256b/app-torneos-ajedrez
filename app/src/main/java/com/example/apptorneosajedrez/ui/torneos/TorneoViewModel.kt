package com.example.apptorneosajedrez.ui.torneos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptorneosajedrez.data.TorneosProvider
import com.example.apptorneosajedrez.model.TorneoModel

class TorneoViewModel : ViewModel() {
    private val _torneos = MutableLiveData<List<TorneoModel>>()
    val torneos: LiveData<List<TorneoModel>> get() = _torneos

    fun cargarTorneos() {
        _torneos.value = TorneosProvider.obtenerTorneos()
    }
}