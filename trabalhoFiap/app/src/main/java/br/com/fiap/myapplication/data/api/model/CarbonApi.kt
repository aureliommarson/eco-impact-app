package br.com.fiap.myapplication.data.api

import br.com.fiap.myapplication.data.model.EstimateRequest
import br.com.fiap.myapplication.data.model.EstimateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CarbonApi {
    // ATENÇÃO: API KEY gerada no site da Carbon Interface.
    @Headers(
        "Authorization: Bearer TgTGmTsELEbExpnFdhIwdQ",
        "Content-Type: application/json"
    )
    @POST("api/v1/estimates")
    suspend fun calculateCarbon(@Body request: EstimateRequest): Response<EstimateResponse>
}