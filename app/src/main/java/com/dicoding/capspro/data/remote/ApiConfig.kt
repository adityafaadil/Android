package com.dicoding.capspro.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    fun provideApiService(): ApiService {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.mapbox.com/geocoding/v5/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(provideOkHttpClient())
//            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://capstone-b21-cap0156.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}