package com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unify.Data.Models.Post
import com.example.unify.databinding.MypostsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MyPostAdapter(var context: Context, var postList: ArrayList<Post>,var type:Int) :
    RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: MypostsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MypostsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.e("Firestore", "postList size: ${postList.size}")
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("Firestore", "Loading image at position $position")
//        holder.binding.postimg
        Log.e("Picasso", "Image is at ${postList[position].postUrl.toString()}")
        Picasso.get().load(postList[position].postUrl).into(holder.binding.postimg, object :
            Callback {
            override fun onSuccess() {
                Log.e("Picasso", "Image loaded successfully at position $position")
            }

            override fun onError(e: Exception?) {
                Log.e("Picasso", "Error loading image at position $position: ${e?.message}")
            }
        })
    }
}
