package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imdmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCadastro.setOnClickListener{
            val i = Intent(this, CadastroProdutoActivity::class.java)
            startActivity(i)
        }
        binding.btListar.setOnClickListener{
            val j = Intent(this, ListagemProdutoActivity::class.java)
            startActivity(j)
        }
        binding.btAlterar.setOnClickListener{
            val k = Intent(this, AlteracaoProdutoActivity::class.java)
            startActivity(k)
        }
        binding.btDeletarMenu.setOnClickListener{
            val l = Intent(this, ExclusaoProdutoActivity::class.java)
            startActivity(l)
        }
    }
}