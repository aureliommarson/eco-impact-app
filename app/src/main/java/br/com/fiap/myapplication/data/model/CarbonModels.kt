package br.com.fiap.myapplication.data.model

data class CarbonIntensityResponse(
    val data: List<IntensityData>
)

data class IntensityData(
    val from: String,
    val to: String,
    val intensity: IntensityDetails
)

data class IntensityDetails(
    val forecast: Int,
    val actual: Int?,
    val index: String
)