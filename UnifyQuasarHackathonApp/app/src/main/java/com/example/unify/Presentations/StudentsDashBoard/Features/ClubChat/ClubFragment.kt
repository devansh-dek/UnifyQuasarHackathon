package com.example.unify.Presentations.StudentsDashBoard.Features.ClubChat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.unify.R
import com.example.unify.databinding.FragmentClubBinding


class ClubFragment : Fragment() {
    val binding by lazy{
        FragmentClubBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val intent = Intent(context, PersonalClubChat::class.java)

        binding.cpwing.setOnClickListener {
//            Toast.makeText(context,"Clicked CP WING", Toast.LENGTH_SHORT).show()
            intent.putExtra("name","CPWING")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context?.startActivity(intent)
        }
        binding.Sdwing.setOnClickListener {
//            Toast.makeText(context,"Clicked CP WING", Toast.LENGTH_SHORT).show()
            intent.putExtra("name","SD WING")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context?.startActivity(intent)
        }
        binding.pdmwing.setOnClickListener {
//            Toast.makeText(context,"Clicked CP WING", Toast.LENGTH_SHORT).show()
            intent.putExtra("name","PDM WING")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context?.startActivity(intent)
        }
        binding.animewing.setOnClickListener {
//            Toast.makeText(context,"Clicked CP WING", Toast.LENGTH_SHORT).show()
            intent.putExtra("name","ANIME WING")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context?.startActivity(intent)
        }

        return binding.root
    }

    companion object {

    }
}