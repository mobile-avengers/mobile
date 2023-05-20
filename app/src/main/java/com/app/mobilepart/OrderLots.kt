package com.app.mobilepart

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.LotAdapter
import com.app.mobilepart.databinding.ActivityMyOrdersBinding
import com.app.mobilepart.databinding.ActivityOrderLotsBinding
import com.app.mobilepart.model.LotModel

class OrderLots : AppCompatActivity() {

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
        getLotsByOrder()

        adapter.refresh(
            listOf(LotModel(1,"a",0.1f, "hgfdh", "25", "1", "fdfghjkkh"))
        )
    }

    // ручку допилите
    private fun getLotsByOrder() {
        Toast.makeText(this, "гет экстра ${intent.getIntExtra("order_id",0)}", Toast.LENGTH_SHORT).show()
    }


}