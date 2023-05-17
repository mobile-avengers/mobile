package com.app.mobilepart.repository

import com.app.mobilepart.api.RetrofitInstance
import com.app.mobilepart.model.PingModel
import retrofit2.Call

class OrderServiceRepository {
    fun ping(): Call<PingModel?>? {
        return RetrofitInstance.api.ping()
    }
}