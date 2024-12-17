package com.example.imdmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityAlteracaoProdutoBinding
import com.example.imdmarket.model.BancoProdutos
import com.example.imdmarket.model.Produto

class AlteracaoProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlteracaoProdutoBinding
    private lateinit var banco: BancoProdutos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        banco = BancoProdutos(this)
        binding = ActivityAlteracaoProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btLimparAlteracao.setOnClickListener {
            var codigo = binding.editCodAlterar.text.toString().toLongOrNull()

            if (codigo != null) {
                // Buscar o produto pelo código
                val produto = banco.buscarporcodigo(codigo)

                if (produto != null) {
                    // Se o produto for encontrado, preenche os campos de edição
                    binding.editCodAlterar.setText(produto.codigo_produto.toString())
                    binding.editNomeAlterar.setText(produto.nome_produto)
                    binding.editDescricaoAlterar.setText(produto.descricao_produto)
                    binding.editEstoqueAlterar.setText(produto.estoque.toString())
                    Toast.makeText(this, "Produto encontrado!", Toast.LENGTH_LONG).show()
                } else {
                    // Produto não encontrado
                    Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_LONG).show()
                }
            } else {
                // Código inválido
                Toast.makeText(this, "Por favor, insira um código válido!", Toast.LENGTH_LONG).show()
            }
        }

        // Botão para alterar os dados do produto
        binding.btAltera.setOnClickListener {
            var codigo = binding.editCodAlterar.text.toString().toLongOrNull()
            var nome = binding.editNomeAlterar.text.toString()
            var descricao = binding.editDescricaoAlterar.text.toString()
            var estoque = binding.editEstoqueAlterar.text.toString().toIntOrNull()

            // Verificar se todos os campos estão preenchidos corretamente
            if (codigo != null && nome.isNotEmpty() && descricao.isNotEmpty() && estoque != null) {
                // Alterar os dados do produto
                val produtoAlterado = banco.alterar(codigo, nome, descricao, estoque)

                if (produtoAlterado > 0) {
                    // Limpar os campos após a alteração
                    binding.editCodAlterar.setText("")
                    binding.editNomeAlterar.setText("")
                    binding.editDescricaoAlterar.setText("")
                    binding.editEstoqueAlterar.setText("")

                    // deu certo
                    Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_LONG).show()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                } else {
                    // Falha na alteração
                    Toast.makeText(this, "Falha ao alterar produto!", Toast.LENGTH_LONG).show()
                }
            } else {
                // caso, não tenha todos preenchidos
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
