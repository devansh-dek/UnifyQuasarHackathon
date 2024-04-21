package com.example.unify.Presentations.FragmentsformainPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.SearchOptionAdapter
import com.example.unify.R
import com.example.unify.Utils.USER_NODE
import com.example.unify.databinding.FragmentSearchBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class SearchFragment : Fragment() {
lateinit var binding :  FragmentSearchBinding
lateinit var adapter : SearchOptionAdapter
var userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchBinding.inflate(layoutInflater,container,false)

        binding.searchidsrv.layoutManager = LinearLayoutManager(requireContext())
        adapter = SearchOptionAdapter(requireContext(),userList )
        binding.searchidsrv.adapter= adapter
        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var tempList = ArrayList<User>()
            userList.clear()
            for(i in it.documents){
                if(i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())){

                }
                else{
                    var user : User= i.toObject<User>()!!
                    tempList.add(user)
                }


            }
            userList.addAll(tempList)
            adapter.notifyDataSetChanged()




        }
        binding.searchbtn.setOnClickListener {
            var textsearch = binding.searchView.text.toString()
            Firebase.firestore.collection(USER_NODE).whereEqualTo("name",textsearch).get().addOnSuccessListener {
                var tempList = ArrayList<User>()
                userList.clear()
                for(i in it.documents){
                    if(i.id.toString().equals(Firebase.auth.currentUser!!.uid.toString())){

                    }
                    else{
                        var user : User= i.toObject<User>()!!
                        tempList.add(user)
                    }


                }
                userList.addAll(tempList)
                adapter.notifyDataSetChanged()




            }


        }

        return binding.root
    }

    companion object {

    }
}