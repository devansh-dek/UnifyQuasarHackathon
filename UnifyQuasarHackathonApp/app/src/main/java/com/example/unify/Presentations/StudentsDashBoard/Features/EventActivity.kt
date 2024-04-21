package com.example.unify.Presentations.StudentsDashBoard.Features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unify.R
import com.example.unify.databinding.ActivityEventBinding

class EventActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityEventBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.materialToolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar2.setNavigationOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }
}