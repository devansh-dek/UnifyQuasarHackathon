package com.example.unify.Presentations.ChattingSystem.ChatAdapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unify.Data.Models.Message
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.User
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

class PersonalChatAdapter(var context: Context, var msgList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    inner class ViewHolder(var binding: ChatsingleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1){
            val view : View = LayoutInflater.from(context).inflate(R.layout.receivemessage,parent,false)
            return  ReceiveViewHolder(view)
        }
        else{
            val view : View = LayoutInflater.from(context).inflate(R.layout.sentmessage,parent,false)
            return  SentViewHolder(view)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMsg = msgList[position]


        if(holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMsg.message
        }else{
            val viewHolder = holder as ReceiveViewHolder
            holder.recieveMessage.text = currentMsg.message

        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = msgList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }
        else{
            return ITEM_RECEIVE
        }

    }


    override fun getItemCount(): Int {
        return msgList.size
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.messagesent)
    }
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recieveMessage = itemView.findViewById<TextView>(R.id.messagereceive)

    }


}
