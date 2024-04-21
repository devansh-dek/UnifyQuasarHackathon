package com.example.unify.Presentations.ChattingSystem.ChatScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.ChattingSystem.ChatAdapters.ChatScreenAdapter
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.StoriesAdapter
import com.example.unify.R
import com.example.unify.Utils.USER_NODE
import com.example.unify.databinding.ActivityChatsViewBinding
import com.example.unify.databinding.ActivityLoginScreenBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class ChatsView : AppCompatActivity() {
    private val binding by lazy{
        ActivityChatsViewBinding.inflate(layoutInflater)

    }

    var followList = ArrayList<User>()
    lateinit var adapter : ChatScreenAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // Stories Adapter Setup
        adapter= ChatScreenAdapter(this,followList)
        binding.chatsrv.layoutManager = LinearLayoutManager(this)
        binding.chatsrv.adapter = adapter

        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var TempList = ArrayList<User>()
            followList.clear()
            for(i in it.documents){
                var user : User = i.toObject<User>()!!
                var adduser = User(user.image,user.name,user.email,user.password,i.id)
                TempList.add(adduser)

            }
            followList.addAll(TempList)
            adapter.notifyDataSetChanged()

        }



    }
}