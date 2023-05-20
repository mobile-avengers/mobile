package com.app.mobilepart

import android.os.Bundle
import android.view.View
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
        binding.addLotButton.setOnClickListener(::createOnClick)
    }

    //Отправляет лот в БД и закрывает активити
    private fun createOnClick(view: View) {
        createLot(getLotFromForm())
        finish()
    }

    //Достает лот из формы
    private fun getLotFromForm(): LotModel {
        val id = 1
        val name = binding.editTextName.text.toString()
        val cost = binding.editTextCost.text.toString().toFloat()
        val link = binding.editTextLink.text.toString()
        val size = binding.editTextSize.textSize.toString()
        val color = binding.editTextColor.text.toString()
        val desk = "!!он нам не нужон!!"
        return LotModel(id,name, cost, link, size, color)
    }

    private fun createLot(lot: LotModel) {
        val call = repository.addLotToCart(lot.id, lot)
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