package com.app.mobilepart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.mobilepart.databinding.ActivityCreateMenuBinding

class CreateMenu: AppCompatActivity() {

    private lateinit var binding: ActivityCreateMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}