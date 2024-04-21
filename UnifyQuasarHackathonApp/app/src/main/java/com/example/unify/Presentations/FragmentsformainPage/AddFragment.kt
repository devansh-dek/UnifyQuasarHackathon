package com.example.unify.Presentations.FragmentsformainPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unify.Presentations.PostsandReels.PostsActivity
import com.example.unify.Presentations.PostsandReels.ReelsActivity
import com.example.unify.R
import com.example.unify.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddFragment : BottomSheetDialogFragment() {

private lateinit var binding : FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false)
      binding.addPost.setOnClickListener {
          startActivity(Intent(activity,PostsActivity::class.java))
      }
        return binding.root
    }

    companion object {


    }
}