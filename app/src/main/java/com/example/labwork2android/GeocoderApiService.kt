package com.example.labwork2android

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://geocode-maps.yandex.ru"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface GeocoderApiService {
    @GET("1.x")
    suspend fun getPoints(@Query("apikey") apiKey: String, @Query("geocode")address: String, @Query("format") format: String) : String
}

object GeocoderApi {
    val retrofitService: GeocoderApiService by lazy { retrofit.create(GeocoderApiService::class.java) }
}