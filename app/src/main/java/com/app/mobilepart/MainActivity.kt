package com.app.mobilepart

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityMainBinding
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.model.OrderModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var repository: OrderServiceRepository = OrderServiceRepository()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createButton.setOnClickListener(::openCreateMenu)
        binding.myLotsButton.setOnClickListener(::openMyLots)
        binding.ordersButton.setOnClickListener(::openMyOrders)
    }

    private fun openCreateMenu(view: View) {
        val createMenuIntent = Intent(this, CreateMenu::class.java)
        startActivity(createMenuIntent)
    }

    private fun openMyLots(view: View) {
        val myLotsIntent = Intent(this, MyLots::class.java)
        startActivity(myLotsIntent)
    }

    private fun openMyOrders(view: View) {
        val myOrdersIntent = Intent(this, MyOrders::class.java)
        startActivity(myOrdersIntent)
    }

    fun inDev(view: View) {
        val devToast =  Toast.makeText(this, "In, dev!", Toast.LENGTH_SHORT)
        devToast.show()

//        createOrder(1, listOf(1, 2, 3))
//        getOrdersByUserId(1)
//        getLotsFromOrder(4) // передаём id заказа!
    }

    private fun createOrder(userId: Int, productIds: List<Int>) {
        val call = repository.createNewOrder(userId, productIds)
        call.enqueue(object : Callback<OrderModel> {
            override fun onResponse(
                call: Call<OrderModel>,
                response: Response<OrderModel>
            ) {
                // ...
            }

            override fun onFailure(call: Call<OrderModel>, t: Throwable) {
                throw t
            }
        })
    }

    private fun getOrdersByUserId(userId: Int) {
        val call = repository.getOrders(userId)
        call.enqueue(object : Callback<List<OrderModel>> {
            override fun onResponse(
                call: Call<List<OrderModel>>,
                response: Response<List<OrderModel>>
            ) {
                val orders = response.body()
                Log.d(TAG, orders.toString())
                // ...
            }

            override fun onFailure(call: Call<List<OrderModel>>, t: Throwable) {
                throw t
            }
        })
    }

    private fun getLotsFromOrder(orderId: Int) {
        val call = repository.getLotsFromOrder(orderId)
        call.enqueue(object : Callback<List<LotModel>> {
            override fun onResponse(
                call: Call<List<LotModel>>,
                response: Response<List<LotModel>>
            ) {
                val lots = response.body()
                Log.d(TAG, lots.toString())
                // ...
            }

            override fun onFailure(call: Call<List<LotModel>>, t: Throwable) {
                throw t
            }
        })
    }
}