package com.app.mobilepart.services

import com.app.mobilepart.Constants
import com.app.mobilepart.model.PingModel
import khttp.responses.Response

class PingService {
    fun ping(): PingModel {
        val response : Response = khttp.get(Constants.PING_ORDER_SERVICE)
        return PingModel(response.jsonObject["status"].toString())
    }
}