package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imdmarket.databinding.ActivityAlteracaoProdutoBinding

class AlteracaoProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlteracaoProdutoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAlteracaoProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btAltera.setOnClickListener{
            val i = Intent(this,  MainActivity::class.java)
            startActivity(i)
        }
    }
}