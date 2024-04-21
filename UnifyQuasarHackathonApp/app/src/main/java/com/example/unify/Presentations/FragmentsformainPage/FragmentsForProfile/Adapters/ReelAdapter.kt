package com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.Reel
import com.example.unify.databinding.MypostsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ReelAdapter(var context: Context, var reelList: ArrayList<Reel>, var type:Int) :
    RecyclerView.Adapter<ReelAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: MypostsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MypostsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.e("Firestore", "reelList size: ${reelList.size}")
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    Glide.with(context).load(reelList.get(position).reelUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(holder.binding.postimg)
    }
}
