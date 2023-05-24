package com.app.mobilepart

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityAdminMenuBinding
import com.app.mobilepart.model.OrderModel
import com.app.mobilepart.repository.OrderServiceRepository
import com.app.mobilepart.tools.CreateHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminMenu : AppCompatActivity() {
    private var repository: OrderServiceRepository = OrderServiceRepository()
    private val helper: CreateHelper = CreateHelper();
    private lateinit var binding: ActivityAdminMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.changeConditionButton.setOnClickListener(::changeCondition)
    }

    private fun changeCondition(view: View) {
        //Вот id ордера

        val orderId = intent.getIntExtra("order_id",0)
        val newCondition = binding.editCondition.text.toString()
        changeOrderCondition(orderId, newCondition)
    }

    private fun changeOrderCondition(orderId: Int, newStatus: String) {
        val call = repository.changeOrderStatus(orderId, newStatus)
        call.enqueue(object : Callback<OrderModel> {
            override fun onResponse(
                call: Call<OrderModel>,
                response: Response<OrderModel>
            ) {
                if(response.code() == 400 || response.code() == 404){
                    getToast("$newStatus is incorrect condition for order!")
                }

                val order: OrderModel? = response.body()
                if (order==null) {
//                    getToast("connection to order service failed")
                } else {
                    Log.d(ContentValues.TAG, order.toString())
                }
            }

            override fun onFailure(call: Call<OrderModel>, t: Throwable) {
                throw t
            }
        })
    }

    private fun getToast(text: String) {
        Toast.makeText(
            this@AdminMenu,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}
