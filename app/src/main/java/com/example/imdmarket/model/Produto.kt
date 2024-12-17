package com.example.imdmarket.model

class Produto(var codigo_produto: Long = 0, var nome_produto: String = "", var descricao_produto: String = "", var estoque : Int= 0) {

    override fun toString(): String {
        return "Codigo Produto: $codigo_produto \n Nome Produto: $nome_produto \n Descricao Produto: $descricao_produto \n Estoque: $estoque"
    }


}