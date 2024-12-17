package com.example.imdmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityExclusaoProdutoBinding
import com.example.imdmarket.model.BancoProdutos

class ExclusaoProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExclusaoProdutoBinding
    private lateinit var banco : BancoProdutos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityExclusaoProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        banco = BancoProdutos(this)

        binding.btDeletar.setOnClickListener{
            val codigo = binding.edCodigo.text.toString().toLong()

            val resultado = banco.deletar(codigo)

            if (resultado > 0) {
                Toast.makeText(this, "Produto deletado com sucesso!", Toast.LENGTH_LONG).show()
                binding.edCodigo.setText("")

                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this, "Produto não encontrado.", Toast.LENGTH_LONG).show()
            }
        }
        binding.btLimpar.setOnClickListener {
            binding.edCodigo.setText("")
        }

    }
}