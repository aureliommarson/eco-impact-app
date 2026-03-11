package br.com.fiap.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.myapplication.R
import br.com.fiap.myapplication.ui.calculator.SelectActivity

/*
 * Activity principal do aplicativo.
 * Essa é a primeira tela que o usuário vê.
 * Ela mostra o botão para iniciar o cálculo de emissão de carbono.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o layout da tela inicial
        setContentView(R.layout.activity_main)

        // Configura o botão "Começar"
        findViewById<Button>(R.id.btnStart).setOnClickListener {

            // Abre a tela onde o usuário escolhe o tipo de cálculo
            startActivity(Intent(this, SelectActivity::class.java))

            // Aplica animação na troca de tela
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}