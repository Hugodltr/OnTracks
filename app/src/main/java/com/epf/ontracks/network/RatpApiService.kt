package com.epf.ontracks.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

// API url
private const val BASE_URL = "https://api-ratp.pierre-grimaud.fr/v4/"

// create a retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// retrofit request
interface RatpApiService {
    @GET("lines")
    fun getProperties():
            Call<String>
}

// init the retrofit service
object RatpApi {
    val retrofitService : RatpApiService by lazy {
        retrofit.create(RatpApiService::class.java) }
}