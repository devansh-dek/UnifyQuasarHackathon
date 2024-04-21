package com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unify.Data.Models.Post
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.MyPostAdapter
import com.example.unify.R
import com.example.unify.databinding.FragmentPostsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class PostsFragment : Fragment() {
   private lateinit var binding: FragmentPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(inflater,container,false)
        var postList = ArrayList<Post>()
        var adapter = MyPostAdapter(requireContext(), postList ,2)
        binding.posts.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.posts.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

//            Toast.makeText(context,"Done ",Toast.LENGTH_SHORT).show()
            Log.e("Firestore", "Document snapshot count: ${it.size()}")

            var tempList = arrayListOf<Post>()
            for (i in it.documents){
                var post: Post? = i.toObject<Post>()
                if (post != null) {
                    tempList.add(post)
//                    Toast.makeText(context,"post added ",Toast.LENGTH_SHORT).show()
                    Log.e("Firestore", "Post added: $post")

                }

            }
//            Toast.makeText(context,"size ${tempList.size} ",Toast.LENGTH_SHORT).show()
            Log.e("Firestore", "TempList size: ${tempList.size}")
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents: ", exception)
            }



        return binding.root
    }

    companion object {

    }
}