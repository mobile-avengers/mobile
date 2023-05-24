package com.app.mobilepart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityCreateMenuBinding
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.repository.OrderServiceRepository
import com.app.mobilepart.tools.CreateHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateMenu: AppCompatActivity() {
    private var repository: OrderServiceRepository = OrderServiceRepository()
    private val helper: CreateHelper = CreateHelper();
    private lateinit var binding: ActivityCreateMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addLotButton.setOnClickListener(::createOnClick)
    }

    private fun createOnClick(view: View) {
        val lotModel = getLotFromForm();
        if (helper.checkLink(lotModel.link)) {
            createLot(1, lotModel)
            finish()
        } else {
            Toast.makeText(
                this@CreateMenu,
                "Укажите одну ссылку на https://dw4.co/t/A/",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //Достает лот из формы
    private fun getLotFromForm(): LotModel {
        val id = -1
        val name = binding.editTextName.text.toString()
        val cost = binding.editTextCost.text.toString().toFloat()
        val link = binding.editTextLink.text.toString()
        val size = binding.editTextSize.text.toString()
        val color = binding.editTextColor.text.toString()
        return LotModel(id,name, cost, link, size, color)
    }

    private fun createLot(userId: Int, lot: LotModel) {
        val call = repository.createNewLotAndAddToCart(userId, lot)
        call.enqueue(object : Callback<LotModel> {
            override fun onResponse(
                call: Call<LotModel>,
                response: Response<LotModel>
            ) {
                if (response.code() != 200){
                    Toast.makeText(
                        this@CreateMenu,
                        "http request failed with code ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val createdLot = response.body()
                // ...
            }

            override fun onFailure(call: Call<LotModel>, t: Throwable) {
                throw t
            }
        })
    }
}