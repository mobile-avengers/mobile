package com.app.mobilepart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityCreateMenuBinding
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateMenu: AppCompatActivity() {
    private var repository: OrderServiceRepository = OrderServiceRepository()
    private lateinit var binding: ActivityCreateMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        createLot(1, LotModel(1, "название", 300.0f,  "ссылка", "размер", "цвет", "описание"))
    }

    private fun createLot(userId: Int, lot: LotModel) {
        val call = repository.addLotToCart(userId, lot)
        call.enqueue(object : Callback<LotModel> {
            override fun onResponse(
                call: Call<LotModel>,
                response: Response<LotModel>
            ) {
                val createdLot = response.body()
                // ...
            }

            override fun onFailure(call: Call<LotModel>, t: Throwable) {
                throw t
            }
        })
    }
}