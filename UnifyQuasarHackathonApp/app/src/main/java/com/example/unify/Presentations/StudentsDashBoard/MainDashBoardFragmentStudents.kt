package com.example.unify.Presentations.StudentsDashBoard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unify.Presentations.StudentsDashBoard.Features.EventActivity
import com.example.unify.R
import com.example.unify.databinding.ActivityDashBoardStudentsBinding
import com.example.unify.databinding.FragmentMainDashBoardStudentsBinding


class MainDashBoardFragmentStudents : Fragment() {
    lateinit var binding: FragmentMainDashBoardStudentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainDashBoardStudentsBinding.inflate(layoutInflater)







        return binding.root

    }

    companion object {

    }
}