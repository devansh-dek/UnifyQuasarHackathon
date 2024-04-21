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
import com.example.unify.databinding.MypostsBinding
import com.example.unify.databinding.SinglestoriesBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso




class StoriesAdapter(var context: Context, var followList: ArrayList<User>) :
    RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: SinglestoriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SinglestoriesBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.e("Firestore", "followList size: ${followList.size}")
        return followList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Glide.with(context).load(followList.get(position).image).placeholder(R.drawable.baseline_account_circle_24).into(holder.binding.profileImage)
        holder.binding.profilename.text = followList.get(position).name
    }
}
