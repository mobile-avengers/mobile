package com.app.mobilepart.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:10101"

    private val retrofit by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.interceptors().add(interceptor)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: OrderServicedApi by lazy {
        retrofit.create(OrderServicedApi::class.java)
    }
}
