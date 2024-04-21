package com.example.unify.Presentations.FragmentsformainPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.PostsFragment
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.ProfilePageAdapter
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.ReelsFragment
import com.example.unify.Presentations.SignUpScreen.SignUpScreen
import com.example.unify.R
import com.example.unify.Utils.USER_NODE
import com.example.unify.databinding.FragmentProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso
import com.google.android.material.tabs.TabLayout
class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var profilePageAdapter: ProfilePageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentProfileBinding.inflate(inflater,container,false)
        binding.editTags.setOnClickListener {
            val intent = Intent(activity,SignUpScreen::class.java)
            intent.putExtra("MODE",1)
            startActivity(intent)

        }
        profilePageAdapter = ProfilePageAdapter(requireActivity().supportFragmentManager)
        profilePageAdapter.addFragments(PostsFragment(),"My Post")
        profilePageAdapter.addFragments(ReelsFragment(),"My Reels")
        binding.viewPager.adapter = profilePageAdapter
binding.tableLayout.setupWithViewPager(binding.viewPager)


        return binding.root


    }

    companion object {

    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user: User= it.toObject<User>()!!
                binding.name.text = user.name.toString()
                binding.tags.text = user.email.toString()
                if(!user.image.isNullOrEmpty()){
Picasso.get().load(user.image).into(binding.profileImage)
                }
            }

    }
}