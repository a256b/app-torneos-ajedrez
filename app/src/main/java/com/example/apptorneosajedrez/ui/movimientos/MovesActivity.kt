package com.example.apptorneosajedrez.ui.movimientos

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
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

        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        adapter = MovesAdapter(emptyList())
        binding.rvMoves.apply {
            layoutManager = LinearLayoutManager(this@MovesActivity)
            adapter = this@MovesActivity.adapter
        }

        vm.moves.observe(this) { list ->
            adapter.update(list)
            binding.rvMoves.scrollToPosition(list.size - 1)
        }

        val chessMoveRegex = Regex(
            """^(?:(?:O-O(?:-O)?)|(?:[RDTAC]?[a-h]?[1-8]?x?[a-h][1-8](?:=[RDTAC])?))[+#]?(?:[!?]{1,2})?$"""
        )


        val chessCharFilter = InputFilter { source, _, _, _, _, _ ->
            val allowed = "abcdefghABCDEFGH12345678xX=NBRQnbrq+#Oo-"
            if (source.all { it in allowed }) source else ""
        }
        binding.etMoveInput.filters = arrayOf(chessCharFilter)

        binding.btnSend.isEnabled = false

        binding.etMoveInput.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString().trim()
                binding.btnSend.isEnabled = chessMoveRegex.matches(input)
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

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