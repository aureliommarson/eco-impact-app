package br.com.fiap.myapplication.data.api

import br.com.fiap.myapplication.data.model.CarbonIntensityResponse
import retrofit2.Response
import retrofit2.http.GET

interface CarbonApi {
    @GET("intensity")
    suspend fun getCurrentIntensity(): Response<CarbonIntensityResponse>
}