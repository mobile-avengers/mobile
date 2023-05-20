package com.app.mobilepart.api

import com.app.mobilepart.model.LotModel
import com.app.mobilepart.model.PingModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderServicedApi {
    @GET("/ping")
    fun ping(): Call<PingModel?>?

    @GET("/api/v1/cart")
    fun getAllLotsFromCart(
        @Query("userId") userId: Int,
    ): Call<List<LotModel>>

    @POST("/api/v1/cart")
    fun createNewOrderAndAddToCart(
        @Query("userId") userId: Int,
        @Body lot: LotModel,
    ): Call<LotModel>
}
