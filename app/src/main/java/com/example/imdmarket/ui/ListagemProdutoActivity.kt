package com.example.imdmarket.ui


import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityListagemProdutoBinding
import com.example.imdmarket.model.BancoProdutos
import com.example.imdmarket.model.Produto


class ListagemProdutoActivity : AppCompatActivity() {


    private lateinit var binding: ActivityListagemProdutoBinding
    private lateinit var banco : BancoProdutos
    private lateinit var adapter: ArrayAdapter<Produto>
    private lateinit var produtos : ArrayList<Produto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        banco = BancoProdutos(this)
        binding = ActivityListagemProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        produtos = banco.listarProdutos()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produtos)
        binding.listprodutos.adapter = adapter
        produtos.clear()
        produtos.addAll(banco.listarProdutos())
        adapter.notifyDataSetChanged()

        binding.btVoltar.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}
