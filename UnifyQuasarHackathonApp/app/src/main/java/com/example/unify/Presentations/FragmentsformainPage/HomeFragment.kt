package com.example.unify.Presentations.FragmentsformainPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.ChattingSystem.ChatScreen.ChatsView
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.PostMainPageAdapter
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.StoriesAdapter
import com.example.unify.R
import com.example.unify.Utils.POST
import com.example.unify.Utils.USER_NODE
import com.example.unify.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class HomeFragment : Fragment() {
private lateinit var binding:FragmentHomeBinding
private var postList = ArrayList<Post>()
private lateinit var adapter : PostMainPageAdapter
    private var followList = ArrayList<User>()
    private lateinit var adapter2:StoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
adapter = PostMainPageAdapter(requireContext(),postList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.imageView6.setOnClickListener {
            startActivity(Intent(context,ChatsView::class.java))
        }

Firebase.firestore.collection(POST).get().addOnSuccessListener {
var TempList = ArrayList<Post>()
    postList.clear()
    Log.e("POST MAINS","POST ADDITION STARTS! ")
    for(i in it.documents){
        var post : Post = i.toObject<Post>()!!
        Log.e("POST MAINS ","post name is ${post.uid}")
        Log.e("POST MAINS ","post caption is ${post.caption}")
        Log.e("POST MAINS ","post url is ${post.postUrl}")

        TempList.add(post)

    }
    postList.addAll(TempList)
    adapter.notifyDataSetChanged()

}


        // Stories Adapter Setup
        adapter2= StoriesAdapter(requireContext(),followList)
        binding.storiesrv.layoutManager = LinearLayoutManager(requireContext())
        binding.storiesrv.adapter = adapter2

        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var TempList = ArrayList<User>()
            followList.clear()
            for(i in it.documents){
                var user : User = i.toObject<User>()!!

                TempList.add(user)

            }
            followList.addAll(TempList)
            adapter2.notifyDataSetChanged()

        }



        return binding.root
    }

    companion object {

    }

}