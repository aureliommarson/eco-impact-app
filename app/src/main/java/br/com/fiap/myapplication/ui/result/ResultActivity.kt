package br.com.fiap.myapplication.ui.result

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.myapplication.R
import br.com.fiap.myapplication.ui.home.MainActivity

/*
 * Activity responsável por mostrar o resultado do cálculo
 * de emissão de carbono para o usuário.
 */
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o layout da tela de resultado
        setContentView(R.layout.activity_result)

        // Botão de voltar (seta no topo da tela)
        // Fecha a tela atual e retorna para a anterior
        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Botão "Finalizar"
        findViewById<Button>(R.id.btnDone).setOnClickListener {

            // Cria uma intenção para voltar para a tela inicial
            val intent = Intent(this, MainActivity::class.java)

            // Limpa o histórico de telas
            // Isso impede que o usuário volte para o resultado
            // ao apertar o botão "voltar" do celular
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

            // Abre a tela inicial
            startActivity(intent)

            // Fecha a tela atual
            finish()
        }

        // Recebe o valor do cálculo enviado pela tela anterior
        val result = intent.getDoubleExtra("RESULT", 0.0)

        // Mostra o resultado na tela formatado com 2 casas decimais
        findViewById<TextView>(R.id.tvResult).text = String.format("%.2f", result)
    }
}