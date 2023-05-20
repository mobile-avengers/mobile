package com.app.mobilepart

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.OrderAdapter
import com.app.mobilepart.databinding.ActivityMyOrdersBinding
import com.app.mobilepart.model.OrderModel

class MyOrders: AppCompatActivity() {

    private lateinit var binding: ActivityMyOrdersBinding
    private val adapter = OrderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.orderRecycler.layoutManager = LinearLayoutManager(this@MyOrders)
        binding.orderRecycler.adapter = adapter

    }


}