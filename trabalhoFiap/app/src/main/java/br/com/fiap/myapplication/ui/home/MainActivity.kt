package br.com.fiap.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.myapplication.R
import br.com.fiap.myapplication.ui.calculator.FormActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            // Pulando a tela de seleção direto para o formulário para focar na API
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}