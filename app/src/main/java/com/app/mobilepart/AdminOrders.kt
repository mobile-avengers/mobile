package com.app.mobilepart

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.AdminOrderAdapter
import com.app.mobilepart.databinding.ActivityMyOrdersBinding
import com.app.mobilepart.model.OrderModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminOrders: AppCompatActivity() {

    private var repository: OrderServiceRepository = OrderServiceRepository()
    private lateinit var binding: ActivityMyOrdersBinding
    private val adapter = AdminOrderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onResume() {
        super.onResume()
        getOrdersByUserId(1)
    }

    private fun init() {
        binding.orderRecycler.layoutManager = LinearLayoutManager(this@AdminOrders)
        binding.orderRecycler.adapter = adapter
        getOrdersByUserId(1)
    }

    private fun getOrdersByUserId(userId: Int) {
        val call = repository.getAllOrders(userId)
        call.enqueue(object : Callback<List<OrderModel>> {
            override fun onResponse(
                call: Call<List<OrderModel>>,
                response: Response<List<OrderModel>>
            ) {
                val orders : List<OrderModel>? = response.body()

                if (orders == null) {
                    getToast()
                } else {
                    Log.d(ContentValues.TAG, orders.toString())
                    adapter.refresh(orders)
                }
            }

            override fun onFailure(call: Call<List<OrderModel>>, t: Throwable) {
                throw t
            }
        })
    }

    private fun getToast() {
        Toast.makeText(
            this@AdminOrders,
            "connection to order service failed",
            Toast.LENGTH_SHORT
        ).show()
    }
}
