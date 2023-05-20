package com.app.mobilepart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityMainBinding
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

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

//        getLots(1)
    }


}