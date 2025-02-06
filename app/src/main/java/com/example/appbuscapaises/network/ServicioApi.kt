package com.example.appbuscapaises.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.appbuscapaises.modelo.Pais

interface ServicioApi {
    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): List<Pais>
    @GET("v3.1/all")
    suspend fun getAllCountries(): List<Pais>

    companion object {
        val api: ServicioApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://restcountries.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServicioApi::class.java)
        }
    }
}