package com.example.apptorneosajedrez.ui.mapa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptorneosajedrez.data.MarcadorRepository
import com.example.apptorneosajedrez.model.Marcador
import com.google.firebase.firestore.ListenerRegistration

class MapaViewModel : ViewModel() {

    private val repositorio = MarcadorRepository()
    private var listener: ListenerRegistration? = null

    private val _marcadores = MutableLiveData<List<Marcador>>()
    val marcadores: LiveData<List<Marcador>> get() = _marcadores

    init {
        listener = repositorio.escucharMarcadores { lista ->
            _marcadores.postValue(lista)
        }
    }

    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }
}

