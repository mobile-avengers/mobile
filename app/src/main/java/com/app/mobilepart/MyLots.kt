package com.app.mobilepart

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.LotAdapter
import com.app.mobilepart.databinding.ActivityMyLotsBinding
import com.app.mobilepart.model.LotList
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.model.OrderModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyLots : AppCompatActivity() {

    private var repository: OrderServiceRepository = OrderServiceRepository()

    private lateinit var binding: ActivityMyLotsBinding
    private val adapter = LotAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLotsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.createOrderButton.setOnClickListener(::createOnclick)
    }


    private fun init() {
        binding.lotRecycler.layoutManager = LinearLayoutManager(this@MyLots)
        binding.lotRecycler.adapter = adapter
        getLots(1)
    }

    private fun getLots(userId: Int) {
        val call = repository.getAllLotsFromCart(userId)
        call.enqueue(object : Callback<List<LotModel>> {
            override fun onResponse(
                call: Call<List<LotModel>?>,
                response: Response<List<LotModel>?>
            ) {
                val lots: List<LotModel>? = response.body()
                if (lots == null) {
                    getToast()
                } else {
                    Log.e(TAG, lots.toString())
                    adapter.refresh(lots)
                }
            }

            override fun onFailure(call: Call<List<LotModel>>, t: Throwable) {
                t.printStackTrace()
                getToast()
            }
        })
    }

    private fun createOnclick(view: View) {
        val list = adapter.getSelectedLotsId()
        if (list.isNotEmpty()) {
            createOrder(1, list)
            finish()
        }
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

    private fun getToast() {
        Toast.makeText(
            this@MyLots,
            "connection to order service failed",
            Toast.LENGTH_SHORT
        ).show()
    }

}