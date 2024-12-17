package com.example.imdmarket.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //criando um sharedPreferences para armazenar login e senha
        val sharedPreferences = this.getSharedPreferences("credenciais", Context.MODE_PRIVATE)

        binding.btEntrar.setOnClickListener {
            val login = binding.edLogin.text.toString()
            val senha = binding.edSenha.text.toString()

            //verificar se os campos não são vazios
            if(login.isNotEmpty() && senha.isNotEmpty()){

                val salvarLogin = sharedPreferences.getString("login", "")
                val salvarSenha = sharedPreferences.getString("senha", "")

                if(login == salvarLogin && senha == salvarSenha){
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Login ou Senha incorretos", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }
        }

        binding.btCadastrar.setOnClickListener {
            val novologin = binding.edLogin.text.toString()
            val novasenha = binding.edSenha.text.toString()

           if(novologin.isNotEmpty() && novasenha.isNotEmpty()){
               val editor = sharedPreferences.edit()
               editor.putString("login", novologin)
               editor.putString("senha", novasenha)
               editor.apply()

               Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()

               binding.edLogin.setText("")
               binding.edSenha.setText("")
           }else{
               Toast.makeText(this, "Erro no cadastro, preencha todos os campos!", Toast.LENGTH_LONG).show()
           }
        }
    }

}