package com.app.mobilepart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobilepart.adapter.LotAdapter
import com.app.mobilepart.databinding.ActivityMyLotsBinding
import com.app.mobilepart.model.LotList
import com.app.mobilepart.model.LotModel
import com.app.mobilepart.repository.OrderServiceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyLots : AppCompatActivity() {

    private var repository: OrderServiceRepository = OrderServiceRepository()

    private lateinit var binding: ActivityMyLotsBinding
    private val adapter = LotAdapter()
    private val lotList = LotList()

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
        getLots(1)
    }

    private fun getLots(userId: Int) {
        val call = repository.getAllLotsFromCart(userId)
        call.enqueue(object : Callback<List<LotModel>> {
            override fun onResponse(
                call: Call<List<LotModel>?>,
                response: Response<List<LotModel>?>
            ) {
                // тут нужно преобразовать в лист лотов нормальный хуй знает что там в боди

                val lots : List<LotModel> = response.body()!!
                adapter.refresh(lots)
//                val responseString = responseFromAPI.toString()
//                binding.ordersButton.text = responseString
            }

            override fun onFailure(call: Call<List<LotModel>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(
                    this@MyLots,
                    "connection to order service failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}