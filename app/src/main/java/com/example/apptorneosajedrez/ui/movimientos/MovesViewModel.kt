package com.example.apptorneosajedrez.ui.movimientos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptorneosajedrez.data.MoveRepository
import com.example.apptorneosajedrez.model.Move

class MovesViewModel : ViewModel() {
    private val repo = MoveRepository()
    private val _moves = MutableLiveData<List<Move>>(emptyList())
    val moves: LiveData<List<Move>> = _moves

    init {
        repo.listenMoves { list ->
            _moves.postValue(list)
        }
    }

    fun sendMove(notation: String) {
        if (notation.isNotBlank()) {
            repo.sendMove(notation)
        }
    }
}
