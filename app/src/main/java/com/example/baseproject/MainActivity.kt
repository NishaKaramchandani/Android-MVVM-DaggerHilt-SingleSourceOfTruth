package com.example.baseproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseproject.application.BaseApplication
import com.example.baseproject.databinding.ActivityMainBinding
import com.example.baseproject.di.DatabaseFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}