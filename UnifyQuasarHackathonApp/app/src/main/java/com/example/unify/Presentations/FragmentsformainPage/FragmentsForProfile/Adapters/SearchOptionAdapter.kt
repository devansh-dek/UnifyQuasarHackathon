package com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.User
import com.example.unify.R
import com.example.unify.Utils.FOLLOW
import com.example.unify.databinding.MypostsBinding
import com.example.unify.databinding.SearchidsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class SearchOptionAdapter(var context: Context, var userList: ArrayList<User>) :
    RecyclerView.Adapter<SearchOptionAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: SearchidsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchidsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isfollow = false
      Glide.with(context).load(userList.get(position).image).placeholder(R.drawable.baseline_person_pin_24).into(holder.binding.profileimage)
        holder.binding.profilename.text = userList.get(position).name

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
            .whereEqualTo("email",userList.get(position).email).get().addOnSuccessListener {
                if(it.documents.size==0){
                    isfollow = false
                }
                else{
                    holder.binding.followbtn.text = "Unfollow"
                    isfollow = true
                }
            }

holder.binding.followbtn.setOnClickListener {
    if(isfollow){
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
            .whereEqualTo("email",userList.get(position).email).get().addOnSuccessListener {
               Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document(it.documents.get(0).id).delete()

          holder.binding.followbtn.text="Follow"
                isfollow = false

            }
    }
    else{
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document()
            .set(userList.get(position))
        holder.binding.followbtn.text = "Unfollow"
        isfollow = true
    }
}




    }
}
