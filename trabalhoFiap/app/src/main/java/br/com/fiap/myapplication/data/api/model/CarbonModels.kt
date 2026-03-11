package br.com.fiap.myapplication.data.model

import com.google.gson.annotations.SerializedName

// Modelo para enviar os dados (Request)
data class EstimateRequest(
    val type: String = "vehicle",
    val distance_unit: String = "km",
    val distance_value: Double,
    val vehicle_model_id: String
)

// Modelos para receber os dados (Response)
data class EstimateResponse(
    val data: EstimateData
)

data class EstimateData(
    val attributes: EstimateAttributes
)

data class EstimateAttributes(
    @SerializedName("carbon_kg")
    val carbonKg: Double
)