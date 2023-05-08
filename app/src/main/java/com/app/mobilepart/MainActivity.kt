package com.app.mobilepart
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.mobilepart.databinding.ActivityMainBinding
import com.app.mobilepart.model.PingModel
import com.app.mobilepart.services.PingService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    var pingService: PingService = PingService()

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

        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute {
            //Background work here
            run()
        }
    }

    fun run() {
        // https://www.baeldung.com/kotlin/khttp
        try {
            val p: PingModel = pingService.ping()
            Log.d("PING STATUS", p.status)
        } catch (e: java.lang.Exception) {
            Log.e("ERR", e.message.toString())
        }
    }

}