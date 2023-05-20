package com.app.mobilepart.model

import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LotList {
    val lots: ArrayList<LotModel> = ArrayList<LotModel>()

    fun update() {
        //тут нужно получить POST с данными и сконвертировать всё в LotModel и положить в lots

        // сейчас просто тест работы функции

        lots.clear()

    }
}