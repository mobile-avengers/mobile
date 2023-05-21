package com.app.mobilepart

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.LotAdapter
import com.app.mobilepart.databinding.ActivityMyOrdersBinding
import com.app.mobilepart.databinding.ActivityOrderLotsBinding
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderLots : AppCompatActivity() {

    private var repository: OrderServiceRepository = OrderServiceRepository()
    private lateinit var binding: ActivityOrderLotsBinding
    private val adapter = LotAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderLotsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.lotRecycler.layoutManager = LinearLayoutManager(this@OrderLots)
        binding.lotRecycler.adapter = adapter
        getLotsFromOrder(intent.getIntExtra("order_id",0))
    }

    private fun getLotsFromOrder(orderId: Int) {
        val call = repository.getLotsFromOrder(orderId)
        call.enqueue(object : Callback<List<LotModel>> {
            override fun onResponse(
                call: Call<List<LotModel>>,
                response: Response<List<LotModel>>
            ) {
                val lots: List<LotModel>? = response.body()
                if (lots==null) {
                    getToast()
                } else {
                    Log.d(ContentValues.TAG, lots.toString())
                    adapter.refresh(lots)
                }
            }

            override fun onFailure(call: Call<List<LotModel>>, t: Throwable) {
                throw t
            }
        })
    }

    private fun getToast() {
        Toast.makeText(
            this@OrderLots,
            "connection to order service failed",
            Toast.LENGTH_SHORT
        ).show()
    }

}