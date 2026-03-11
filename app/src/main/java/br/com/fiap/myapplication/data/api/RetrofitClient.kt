package br.com.fiap.myapplication.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance: CarbonApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.carbonintensity.org.uk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarbonApi::class.java)
    }
}