package br.com.fiap.myapplication.ui.tips

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.myapplication.R

/*
 * Activity responsável por mostrar dicas ao usuário
 * sobre como reduzir a emissão de carbono no dia a dia.
 */
class TipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o layout da tela de dicas
        setContentView(R.layout.activity_tips)

        // Botão de voltar (seta no topo da tela)
        // Fecha a tela atual e retorna para a tela anterior
        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}