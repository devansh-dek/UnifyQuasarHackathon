package com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unify.Data.Models.Reel
import com.example.unify.Presentations.FragmentsformainPage.FragmentsForProfile.Adapters.ReelAdapter
import com.example.unify.R
import com.example.unify.Utils.REEL
import com.example.unify.databinding.FragmentReelBinding
import com.example.unify.databinding.FragmentReelsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class ReelsFragment : Fragment() {
    private lateinit var binding : FragmentReelsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReelsBinding.inflate(layoutInflater,container,false)

        var reelList = ArrayList<Reel>()
        var adapter = ReelAdapter(requireContext(), reelList,1 )
        binding.reelsrv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.reelsrv.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).get().addOnSuccessListener {

            Log.e("Firestore", "REELS snapshot count: ${it.size()}")

            var tempList = arrayListOf<Reel>()
            for (i in it.documents){
                var reel: Reel? = i.toObject<Reel>()
                if (reel != null) {
                    tempList.add(reel)
                    Log.e("Firestore", "Reel added: $reel")

                }

            }
            Log.e("Firestore", "REELTEMPList size: ${tempList.size}")

            reelList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
            .addOnFailureListener { exception ->
                Log.e("Firestore", " REEL Error getting documents: ", exception)
            }

        return binding.root
    }

    companion object {

    }
}