package com.example.imdmarket.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoProdutos(contexto: Context) : SQLiteOpenHelper(contexto, Nome, null, Versao) {

    companion object{
        private const val Nome = "dbImdMarket"
        private const val Versao = 1
    }
    override fun onCreate(db: SQLiteDatabase) {
       db.execSQL(
           """
              CREATE TABLE produtos(
                codigo INTEGER PRIMARY KEY,
                nome TEXT,
                descricao TEXT,
                estoque INTEGER
              ) 
           """

       )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun salvar(codigo: Long, nome:String, descricao:String, estoque: Int):Long{
        val banco = this.writableDatabase
        val container = ContentValues()
        container.put("codigo", codigo)
        container.put("nome", nome)
        container.put("descricao", descricao)
        container.put("estoque", estoque)
        val result = banco.insert("produtos", null, container)
        banco.close()
        return result
    }

   fun alterar(codigo: Long, nome: String, descricao: String, estoque: Int):Int{
       val banco = this.writableDatabase
       val container = ContentValues()
       container.put("codigo", codigo)
       container.put("nome", nome)
       container.put("descricao", descricao)
       container.put("estoque", estoque)
       val result = banco.update("produtos", container, "codigo=" + codigo, null)
       banco.close()
       return result
   }

   fun deletar(codigo: Long):Int{
       val banco = this.writableDatabase
       val result = banco.delete("produtos", "codigo = " + codigo, null)
       banco.close()
       return result
   }

    fun listarProdutos() : ArrayList<Produto>{
        val banco = this.readableDatabase
        var cursor = banco.rawQuery("SELECT * FROM produtos", null)
        var array = ArrayList<Produto>()
        if(cursor.count > 0){
            cursor.moveToFirst()
            do{
                var codigoproduto = cursor.getLong(0)
                var nomeproduto = cursor.getString(1)
                var descricaoproduto = cursor.getString(2)
                var estoqueproduto = cursor.getInt(3)
                array.add(Produto(codigoproduto, nomeproduto, descricaoproduto, estoqueproduto))
            }while(cursor.moveToNext())
        }
        cursor.close()
        banco.close()
        return array;
    }

    fun buscarporcodigo(codigo: Long): Produto{
        val banco = this.readableDatabase
        var cursor = banco.rawQuery("SELECT * FROM produtos WHERE codigo=" + codigo, null)
        var produto = Produto()
        if(cursor.count > 0){
            cursor.moveToFirst()
            do{
                produto.codigo_produto = cursor.getLong(0)
                produto.nome_produto = cursor.getString(1)
                produto.descricao_produto = cursor.getString(2)
                produto.estoque = cursor.getInt(3)
            }while(cursor.moveToNext())
        }
        cursor.close()
        return produto
    }


}
