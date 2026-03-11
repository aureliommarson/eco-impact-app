package br.com.fiap.myapplication.ui.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.fiap.myapplication.R
import br.com.fiap.myapplication.data.api.RetrofitClient
import br.com.fiap.myapplication.data.model.EstimateRequest
import br.com.fiap.myapplication.ui.result.ResultActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val editDistance = findViewById<EditText>(R.id.editDistance)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val distanceStr = editDistance.text.toString()
            if (distanceStr.isNotEmpty()) {
                calculateCarbonImpact(distanceStr.toDouble())
            } else {
                Toast.makeText(this, "Preencha a distância", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateCarbonImpact(distance: Double) {
        // LifecycleScope é usado para rodar a chamada de rede de forma assíncrona (fora da thread principal)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Nota: A API exige um vehicle_model_id válido. Usando um ID de teste genérico da API.
                val request = EstimateRequest(
                    distance_value = distance,
                    vehicle_model_id = "7268a9b7-17e8-4c8d-acca-57059252afe9"
                )

                val response = RetrofitClient.instance.calculateCarbon(request)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val carbonEmitted = response.body()!!.data.attributes.carbonKg
                        showResult(carbonEmitted)
                    } else {
                        Toast.makeText(this@FormActivity, "Erro na API: ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FormActivity, "Falha na conexão: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showResult(carbonKg: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("CARBON_RESULT", carbonKg)
        startActivity(intent)
    }
}