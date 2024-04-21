package com.example.unify.Presentations.ChattingSystem.ChatAdapters
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.ChattingSystem.ChatScreen.PersonalChatScreen
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.StoriesAdapter
import com.example.unify.R
import com.example.unify.Utils.FOLLOW
import com.example.unify.databinding.ChatsingleBinding
import com.example.unify.databinding.MypostsBinding
import com.example.unify.databinding.SearchidsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ChatScreenAdapter(var context: Context, var userList: ArrayList<User>) :
    RecyclerView.Adapter<ChatScreenAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ChatsingleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatsingleBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(userList.get(position).image).placeholder(R.drawable.baseline_account_circle_24).into(holder.binding.profileImage)
        holder.binding.profilename.text = userList.get(position).name
        holder.binding.chatitem.setOnClickListener {
        val intent = Intent(context,PersonalChatScreen::class.java)
    intent.putExtra("name",userList[position].name)
            intent.putExtra("uid",userList[position].uid )
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }


}
