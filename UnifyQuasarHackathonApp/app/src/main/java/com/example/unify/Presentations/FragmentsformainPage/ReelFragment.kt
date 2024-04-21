package com.example.unify.Presentations.FragmentsformainPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.Reel
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.MyPostAdapter
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.MyReelAdapter
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.ReelAdapter
import com.example.unify.R
import com.example.unify.Utils.REEL
import com.example.unify.databinding.FragmentReelBinding
import com.example.unify.databinding.FragmentReelsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

// TODO: Rename parameter arguments, choose names that match

class ReelFragment : Fragment() {
    private lateinit var binding : FragmentReelBinding
    lateinit var adapter: MyReelAdapter
    var reelList = ArrayList<Reel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("REEL VIEW ", "REELS ")


        binding = FragmentReelBinding.inflate(layoutInflater,container,false)
        adapter= MyReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter = adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {

            Log.e("REEL VIEW", "REELS snapshot count: ${it.size()}")

            var tempList = ArrayList<Reel>()
            reelList.clear()
            for (i in it.documents){
                var reel:Reel = i.toObject<Reel>()!!
                if (reel != null) {
                    tempList.add(reel)
                    Log.e("Firestore", "Reel added: ${i.toObject<Reel>()}")

                }

            }
            Log.e("REEL VIEW", "REELTEMPList size: ${tempList.size}")
            reelList.addAll(tempList)
            reelList.reverse()
            adapter.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            Log.e("REEL VIEW", " REEL Error getting documents: ", exception)
        }

        return binding.root
    }

    companion object {

    }
}