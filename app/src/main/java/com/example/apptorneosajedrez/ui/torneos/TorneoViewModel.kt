package com.example.apptorneosajedrez.ui.torneos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptorneosajedrez.data.TorneosProvider
import com.example.apptorneosajedrez.model.Torneo
import kotlinx.coroutines.launch

class TorneoViewModel : ViewModel() {
    private val _torneos = MutableLiveData<List<Torneo>>()
    val torneos: LiveData<List<Torneo>> get() = _torneos

    init {
        viewModelScope.launch {
            try {
                val lista = TorneosProvider.obtenerTorneos()
                _torneos.value = lista
            } catch (e: Exception) {

            }
        }
    }
}