package br.com.fiap.myapplication.data.repository

import br.com.fiap.myapplication.data.api.RetrofitClient
import br.com.fiap.myapplication.ui.calculator.ResultState

class CarbonRepository {
    suspend fun getCarbonEstimate(kwh: Double): ResultState<Double> {
        return try {
            val response = RetrofitClient.instance.getCurrentIntensity()

            if (response.isSuccessful && response.body() != null) {
                // Pega a intensidade de carbono atual da rede elétrica
                val intensityData = response.body()!!.data.firstOrNull()?.intensity
                val actualIntensity = intensityData?.actual ?: intensityData?.forecast ?: 0

                // Cálculo: (Intensidade atual * kWh consumido) / 1000 para converter de gramas para Kgs
                val totalCarbonKg = (actualIntensity * kwh) / 1000.0

                ResultState.Success(totalCarbonKg)
            } else {
                ResultState.Error("Erro na API: ${response.code()}")
            }
        } catch (e: Exception) {
            ResultState.Error("Falha na conexão com a rede: ${e.localizedMessage}")
        }
    }
}