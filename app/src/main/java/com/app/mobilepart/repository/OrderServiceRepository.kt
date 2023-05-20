package com.app.mobilepart.repository

import com.app.mobilepart.api.RetrofitInstance
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.model.PingModel
import retrofit2.Call

class OrderServiceRepository {
    fun ping(): Call<PingModel?>? {
        return RetrofitInstance.api.ping()
    }

    fun getAllLotsFromCart(userId: Int): Call<List<LotModel>> {
        return RetrofitInstance.api.getAllLotsFromCart(userId)
    }

    fun addLotToCart(userId: Int, lot: LotModel): Call<LotModel> {
        return RetrofitInstance.api.createNewOrderAndAddToCart(userId, lot)
    }
}