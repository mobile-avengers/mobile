package com.app.mobilepart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.LotAdapter
import com.app.mobilepart.databinding.ActivityMyLotsBinding
import com.app.mobilepart.model.LotModel

class MyLots : AppCompatActivity() {

    private lateinit var binding: ActivityMyLotsBinding
    private val adapter = LotAdapter()

    private var id = 0 //FIXME выпилить!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLotsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.lotRecycler.layoutManager = LinearLayoutManager(this@MyLots)
        binding.lotRecycler.adapter = adapter
        binding.button.setOnClickListener {
            val lot = LotModel(id, "Подкрадули", 7999.0f)
            adapter.addLot(lot)
            id++
        }
    }
}