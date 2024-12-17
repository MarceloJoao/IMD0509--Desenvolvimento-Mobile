package com.example.imdmarket.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityCadastroBinding
import com.example.imdmarket.model.BancoProdutos
import com.example.imdmarket.model.Produto

class CadastroProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var banco: BancoProdutos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        banco = BancoProdutos(this)
        enableEdgeToEdge()
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSalvarCadastro.setOnClickListener {
            var codigoText = binding.editCodigo.text.toString()
            var nome = binding.editNome.text.toString()
            var descricao = binding.editDescricao.text.toString()
            var estoqueText = binding.editEstoque.text.toString()

            // Verificação dos campos vazios
            if (codigoText.isNotEmpty() && nome.isNotEmpty() && descricao.isNotEmpty() && estoqueText.isNotEmpty()) {
                //uso do try/catch para impedir o fechamento do app
                try {
                    // Converte os valores para os tipos determinados
                    var codigo = codigoText.toLong()
                    var estoque = estoqueText.toInt()

                    banco.salvar(codigo, nome, descricao, estoque)

                    Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_LONG).show()

                    // Limpar os campos após o cadastro
                    binding.editCodigo.setText("")
                    binding.editNome.setText("")
                    binding.editDescricao.setText("")
                    binding.editEstoque.setText("")


                    var i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                } catch (e: Exception) {
                    Toast.makeText(this, "Erro ao salvar o produto. Verifique os dados!", Toast.LENGTH_LONG).show()
                }
            } else {

                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
        }
    }

}

