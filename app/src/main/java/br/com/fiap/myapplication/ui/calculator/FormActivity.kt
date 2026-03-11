package br.com.fiap.myapplication.ui.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.myapplication.R
import br.com.fiap.myapplication.ui.result.ResultActivity

/*
 * Activity responsável pela tela de formulário do cálculo.
 * Aqui o usuário informa os dados (distância, energia, etc.)
 * e o aplicativo calcula a emissão de carbono.
 */
class FormActivity : AppCompatActivity() {

    // Conecta esta tela com o ViewModel responsável pelos cálculos
    private val viewModel: CalculatorViewModel by viewModels()

    // Guarda o tipo de cálculo escolhido pelo usuário
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o layout da tela
        setContentView(R.layout.activity_form)

        // Botão de voltar para a tela anterior
        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }

        // Recebe o tipo de cálculo enviado pela tela anterior
        type = intent.getStringExtra("TYPE") ?: "vehicle"

        // Configura os campos da tela de acordo com o tipo escolhido
        setupUI(type)

        // Observa o resultado do cálculo vindo do ViewModel
        viewModel.estimateResult.observe(this) { state ->

            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            val btnCalculate = findViewById<Button>(R.id.btnCalculate)

            // Verifica o estado atual do cálculo
            when (state) {

                // Enquanto o cálculo está sendo feito
                is ResultState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    btnCalculate.isEnabled = false
                }

                // Quando o cálculo termina com sucesso
                is ResultState.Success -> {
                    progressBar.visibility = View.GONE
                    btnCalculate.isEnabled = true

                    // Abre a tela de resultado
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("RESULT", state.data)
                    startActivity(intent)

                    // Aplica animação de transição entre telas
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }

                // Caso aconteça algum erro
                is ResultState.Error -> {
                    progressBar.visibility = View.GONE
                    btnCalculate.isEnabled = true

                    // Mostra mensagem de erro
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        // Botão que inicia o cálculo
        findViewById<Button>(R.id.btnCalculate).setOnClickListener { buildAndSendRequest() }
    }

    /*
     * Configura os campos da tela dependendo do tipo de cálculo escolhido
     */
    private fun setupUI(type: String) {

        val title = findViewById<TextView>(R.id.tvFormTitle)
        val in1 = findViewById<EditText>(R.id.editInput1)
        val in2 = findViewById<EditText>(R.id.editInput2)
        val in3 = findViewById<EditText>(R.id.editInput3)

        // Esconde campos extras que não serão usados
        in2.visibility = View.GONE
        in3.visibility = View.GONE

        // Ajusta o título e o campo de entrada
        when (type) {

            "vehicle" -> {
                title.text = "Transporte (Carro)"
                in1.apply { visibility = View.VISIBLE; hint = "Distância percorrida (km)" }
            }

            "electricity" -> {
                title.text = "Eletricidade"
                in1.apply { visibility = View.VISIBLE; hint = "Consumo em kWh" }
            }

            "flight" -> {
                title.text = "Viagem Aérea"
                in1.apply { visibility = View.VISIBLE; hint = "Distância do voo (km)" }
            }

            "meat" -> {
                title.text = "Alimentação (Carne)"
                in1.apply { visibility = View.VISIBLE; hint = "Kilos consumidos (kg)" }
            }
        }
    }

    /*
     * Lê os valores digitados pelo usuário e inicia o cálculo
     */
    private fun buildAndSendRequest() {

        val in1Str = findViewById<EditText>(R.id.editInput1).text.toString()

        // Verifica se o campo está vazio
        if (in1Str.isEmpty()) {
            Toast.makeText(this, "Preencha o valor.", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            // Converte o valor digitado para número
            val inputValue = in1Str.toDouble()

            // Se for cálculo de eletricidade, usa a API
            if (type == "electricity") {
                viewModel.calculate(inputValue)

            } else {

                // Cálculo local usando fatores de emissão
                val carbonKg = when (type) {
                    "vehicle" -> inputValue * 0.192
                    "flight" -> inputValue * 0.285
                    "meat" -> inputValue * 27.0
                    else -> 0.0
                }

                // Abre a tela de resultado
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("RESULT", carbonKg)
                startActivity(intent)

                // Animação da troca de tela
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }

        } catch (e: Exception) {

            // Caso ocorra algum erro no cálculo
            Toast.makeText(this, "Erro ao calcular.", Toast.LENGTH_SHORT).show()
        }
    }
}