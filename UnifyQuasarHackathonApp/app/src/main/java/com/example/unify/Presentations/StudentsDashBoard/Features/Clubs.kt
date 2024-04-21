package com.example.unify.Presentations.StudentsDashBoard.Features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.unify.Presentations.ChattingSystem.ChatScreen.PersonalChatScreen
import com.example.unify.Presentations.StudentsDashBoard.Features.ClubChat.PersonalClubChat
import com.example.unify.R
import com.example.unify.databinding.ActivityClubChatBinding

class Clubs : AppCompatActivity() {
    val binding by lazy {
        ActivityClubChatBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = Intent(applicationContext, PersonalClubChat::class.java)

        binding.cpwing.setOnClickListener {
            Toast.makeText(this,"Clicked CP WING", Toast.LENGTH_SHORT).show()
            intent.putExtra("name","cpwing")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            applicationContext.startActivity(intent)
        }



        setContentView(binding.root)
    }
}