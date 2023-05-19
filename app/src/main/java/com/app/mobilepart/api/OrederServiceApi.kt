package com.app.mobilepart.api

import com.app.mobilepart.model.PingModel
import retrofit2.Call
import retrofit2.http.GET

interface OrderServicedApi {
    @GET("/ping")
    fun ping(): Call<PingModel?>?
}
