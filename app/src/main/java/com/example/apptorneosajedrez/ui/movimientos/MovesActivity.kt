package com.example.apptorneosajedrez.ui.movimientos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptorneosajedrez.databinding.ActivityMovesBinding
import com.example.apptorneosajedrez.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MovesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovesBinding
    private val vm: MovesViewModel by viewModels()
    private lateinit var adapter: MovesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewBinding()

        // 1) Asegúrate de que el usuario esté autenticado
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // 2) RecyclerView
        adapter = MovesAdapter(emptyList())
        binding.rvMoves.apply {
            layoutManager = LinearLayoutManager(this@MovesActivity)
            adapter = this@MovesActivity.adapter
        }

        // 3) Observa cambios en Firestore
        vm.moves.observe(this) { list ->
            adapter.update(list)
            binding.rvMoves.scrollToPosition(list.size - 1)
        }

        // 4) Envío de movimientos
        binding.btnSend.setOnClickListener {
            val text = binding.etMoveInput.text.toString().trim()
            vm.sendMove(text)
            binding.etMoveInput.text?.clear()
        }

    }

    private fun initializeViewBinding() {
        enableEdgeToEdge()

        binding = ActivityMovesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.llInput) { view, insets ->
            val navBarHeight = insets
                .getInsets(WindowInsetsCompat.Type.navigationBars())
                .bottom
            view.updatePadding(bottom = navBarHeight)
            insets
        }
    }
}