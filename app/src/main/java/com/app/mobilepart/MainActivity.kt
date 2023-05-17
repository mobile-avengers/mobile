package com.app.mobilepart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityMainBinding
import com.app.mobilepart.model.PingModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var repository: OrderServiceRepository = OrderServiceRepository()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createButton.setOnClickListener(::openCreateMenu)
        binding.myLotsButton.setOnClickListener(::openMyLots)
        binding.ordersButton.setOnClickListener(::inDev)
    }

    private fun openCreateMenu(view: View) {
        val createMenuIntent = Intent(this, CreateMenu::class.java)
        startActivity(createMenuIntent)
    }

    private fun openMyLots(view: View) {
        val myLotsIntent = Intent(this, MyLots::class.java)
        startActivity(myLotsIntent)
    }

    fun inDev(view: View) {
        val devToast =  Toast.makeText(this, "In, dev!", Toast.LENGTH_SHORT)
        devToast.show()

        ping()
    }

    private fun ping() {
        val call = repository.ping()
        call!!.enqueue(object : Callback<PingModel?> {
            override fun onResponse(call: Call<PingModel?>, response: Response<PingModel?>) {
                val responseFromAPI = response.body()
                val responseString = responseFromAPI!!.status
                binding.ordersButton.text = responseString
            }

            override fun onFailure(call: Call<PingModel?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@MainActivity, "connection to order service failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}