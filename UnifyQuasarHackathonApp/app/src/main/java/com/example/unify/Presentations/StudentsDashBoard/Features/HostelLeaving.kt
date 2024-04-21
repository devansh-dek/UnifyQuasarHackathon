package com.example.unify.Presentations.StudentsDashBoard.Features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unify.R
import com.example.unify.databinding.ActivityHostelLeavingBinding

class HostelLeaving : AppCompatActivity() {
    val binding by lazy {
        ActivityHostelLeavingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




    }
}