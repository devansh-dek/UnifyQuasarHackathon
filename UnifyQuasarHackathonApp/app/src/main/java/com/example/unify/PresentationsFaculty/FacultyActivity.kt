package com.example.unify.PresentationsFaculty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unify.databinding.ActivityFacultyBinding

class FacultyActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFacultyBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.attendace.setOnClickListener {
            startActivity(Intent(this,AttendanceActivity::class.java))

        }
        binding.uploadStudyMaterials.setOnClickListener {
//            startActivity(Intent(this,))
        }



        setContentView(binding.root)



    }
}