package com.example.apptorneosajedrez.ui.torneos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptorneosajedrez.data.TorneoRepository
import com.example.apptorneosajedrez.model.Torneo

class TorneoViewModel : ViewModel() {
    private val _torneos = MutableLiveData<List<Torneo>>()
    val torneos: LiveData<List<Torneo>> get() = _torneos

    private val repo = TorneoRepository()

    init {
        repo.escucharTorneos { lista ->
            _torneos.postValue(lista)
        }
    }
}
