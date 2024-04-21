package com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.User
import com.example.unify.R
import com.example.unify.Utils.USER_NODE
import com.example.unify.databinding.MypostsBinding
import com.example.unify.databinding.SinglepostBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PostMainPageAdapter(var context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostMainPageAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: SinglepostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SinglepostBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.e("Firestore", "postList size: ${postList.size}")
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.e("Firestore", "postList UID: ${postList[position].uid}")
        Log.e("Firestore", "postList UID: ${postList[position].postUrl}")
        Log.e("Firestore", "postList UID: ${postList[position].caption}")

        if (postList[position].uid!=null){
            Firebase.firestore.collection(USER_NODE).document(postList.get(position).uid).get().addOnSuccessListener {
                var user = it.toObject<User>()
                Log.e("Firestore USER", "user name: ${user!!.name}")
                Log.e("Firestore USER", "user email: ${user!!.email}")
                Log.e("Firestore USER", "user image: ${user!!.image}")
                if(user!=null){


                Glide.with(context).load(user!!.image).placeholder(R.drawable.baseline_account_circle_24).into(holder.binding.profilePic)
                    if(user!!.name!=null)
                        holder.binding.profilename.text = user!!.name

                }
                else{
                    Log.e("MAIN POSTs ","user is ${user}")
                }


            }
            var isHeart = false
            holder.binding.Like.setOnClickListener {
                if(!isHeart){
                    holder.binding.Like.setImageResource(R.drawable.filledheart)
                    holder.binding.liketext.visibility = View.VISIBLE
                    isHeart = true
                }
                else{
                    holder.binding.Like.setImageResource(R.drawable.heart)
                    holder.binding.liketext.visibility = View.GONE

                    isHeart = false;
                }

            }
            Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.baseline_account_circle_24).into(holder.binding.postImage)
//            holder.binding.Bio.text = postList.get(position).time
//            if(postList.get(position).caption!=null)
            holder.binding.Bio.text = "Today"
            holder.binding.caption.text = postList.get(position).caption.toString()
            holder.binding.Share.setOnClickListener {
                var i = Intent(Intent.ACTION_SEND)
                i.type = "text/plain"
                i.putExtra(Intent.EXTRA_TEXT,"Check our post on Unify -> "+postList.get(position).postUrl)
                context.startActivity(i)

            }
        }

    }
}
