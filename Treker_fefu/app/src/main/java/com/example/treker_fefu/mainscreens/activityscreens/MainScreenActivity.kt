package com.example.treker_fefu.mainscreens.activityscreens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.treker_fefu.databinding.ActivityMainBinding

class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}