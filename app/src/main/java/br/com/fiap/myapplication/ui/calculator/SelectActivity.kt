package br.com.fiap.myapplication.ui.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.myapplication.R
import br.com.fiap.myapplication.ui.tips.TipsActivity

/*
 * Activity responsável por mostrar as opções de cálculo
 * para o usuário escolher. Cada opção representa um tipo
 * de emissão de carbono que pode ser calculada.
 */
class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o layout da tela que será exibido
        setContentView(R.layout.activity_select)

        // Botão de voltar para a tela anterior
        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Cards que representam os tipos de cálculo disponíveis
        // Cada card abre o formulário com um tipo diferente
        findViewById<View>(R.id.cardCar).setOnClickListener { openForm("vehicle") }
        findViewById<View>(R.id.cardEnergy).setOnClickListener { openForm("electricity") }
        findViewById<View>(R.id.cardFlight).setOnClickListener { openForm("flight") }
        findViewById<View>(R.id.cardMeat).setOnClickListener { openForm("meat") }

        // Botão que abre a tela de dicas para reduzir emissão de carbono
        findViewById<Button>(R.id.btnTips).setOnClickListener {

            // Cria uma intenção para abrir a tela de dicas
            startActivity(Intent(this, TipsActivity::class.java))

            // Aplica animação na troca de tela
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    /*
     * Função responsável por abrir a tela de formulário
     * enviando o tipo de cálculo escolhido pelo usuário
     */
    private fun openForm(type: String) {

        // Cria uma intenção para abrir a tela do formulário
        val intent = Intent(this, FormActivity::class.java)

        // Envia o tipo selecionado para a próxima tela
        intent.putExtra("TYPE", type)

        // Abre a tela de formulário
        startActivity(intent)

        // Aplica animação de transição entre telas
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}