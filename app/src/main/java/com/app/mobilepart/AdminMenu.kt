package com.app.mobilepart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityAdminMenuBinding
import com.app.mobilepart.repository.OrderServiceRepository
import com.app.mobilepart.tools.CreateHelper

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

    // TODO CHANGE ORDER CONDITION
    private fun changeCondition(view: View) {
        //Вот id ордера

        val orderID = intent.getIntExtra("order_id",0)
        val newCondition = binding.editCondition.text.toString()
    }




}