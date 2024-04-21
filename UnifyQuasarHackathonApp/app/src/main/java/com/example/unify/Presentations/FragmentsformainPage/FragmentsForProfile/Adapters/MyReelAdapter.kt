package com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.Reel
import com.example.unify.R
import com.example.unify.databinding.MypostsBinding
import com.example.unify.databinding.ReeldesignBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MyReelAdapter(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<MyReelAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ReeldesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ReeldesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.e("Firestore", "reelList size: ${reelList.size}")
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("REEL VIEW ", "REELLSIT PROFILE ${reelList.get(position).profileLink.toString()} ")
        if(!reelList.get(position).profileLink.isEmpty())
        Picasso.get().load(reelList.get(position).profileLink).placeholder(R.drawable.baseline_account_circle_24).into(holder.binding.imageView)
        else
            Picasso.get().load(reelList.get(position).profileLink).placeholder(R.drawable.baseline_account_circle_24).into(holder.binding.imageView)


        holder.binding.caption.setText(reelList.get(position).caption)
        holder.binding.videoView.setVideoPath(reelList.get(position).reelUrl)
        holder.binding.videoView.setOnPreparedListener {
            holder.binding.progressBarReel.visibility = View.GONE
           holder.binding.videoView.start()
        }
    }
}
