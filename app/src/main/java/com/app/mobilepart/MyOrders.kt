package com.app.mobilepart

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.OrderAdapter
import com.app.mobilepart.databinding.ActivityMyOrdersBinding

class MyOrders: AppCompatActivity() {

    private lateinit var binding: ActivityMyOrdersBinding
    private val adapter = OrderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.orderRecycler.layoutManager = LinearLayoutManager(this@MyOrders)
        binding.orderRecycler.adapter = adapter
    }
}