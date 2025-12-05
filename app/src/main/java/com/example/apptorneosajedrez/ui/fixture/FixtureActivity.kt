package com.example.apptorneosajedrez.ui.fixture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptorneosajedrez.databinding.ActivityFixtureBinding
import com.example.apptorneosajedrez.model.Torneo

class FixtureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFixtureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFixtureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val torneo = intent.getSerializableExtra("torneo") as? Torneo

        binding.tvTitulo.text = "Fixture de ${torneo?.nombre ?: ""}"
    }
}
