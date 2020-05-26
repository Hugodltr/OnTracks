package com.epf.ontracks.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// API url
private const val BASE_URL = "https://api-ratp.pierre-grimaud.fr/v4/"

// create a moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// create a retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


// retrofit request
interface RatpApiService {
    @GET("lines")
    fun getLines():
            Deferred<LinesResult>
}

// init the retrofit service
object RatpApi {
    val retrofitService : RatpApiService by lazy {
        retrofit.create(RatpApiService::class.java) }
}