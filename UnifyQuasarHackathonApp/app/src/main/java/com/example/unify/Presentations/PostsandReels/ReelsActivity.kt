package com.example.unify.Presentations.PostsandReels

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.Reel
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.MainHomePage.MainHomeActivity
import com.example.unify.R
import com.example.unify.Utils.POST
import com.example.unify.Utils.POST_FOLDER
import com.example.unify.Utils.REEL
import com.example.unify.Utils.REEL_FOLDER
import com.example.unify.Utils.USER_NODE
import com.example.unify.databinding.ActivityReelsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import uploadImage
import uploadReel

class ReelsActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityReelsBinding.inflate(layoutInflater)
    }
    lateinit var progressDialog : ProgressDialog
 lateinit var videoUrl :String
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri.let {
            if (uri != null) {
                uploadReel(uri, REEL_FOLDER, progressDialog,this){
                        url->
                    if(it!=null){
                        if (url !=  null) {
                            videoUrl= url

                        }
                    }
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.AddImage.setOnClickListener {
            launcher.launch("video/*")

        }
        binding.yesbtn.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user: User= it.toObject<User>()!!
                Log.e("REEL dded","yes btn clicked")
                Log.e("REEL ADDED WITH LINK ","reel link ${videoUrl}")

                val reel= Reel(videoUrl!!,binding.caption.text.toString(),user.image!!)
                binding.yesbtn.setOnClickListener {
                    Firebase.auth.currentUser?.let { firebaseUser ->
                        Firebase.firestore.collection(USER_NODE)
                            .document(firebaseUser.uid)
                            .get()
                            .addOnSuccessListener { documentSnapshot ->
                                val user = documentSnapshot.toObject<User>()
                                user?.let { currentUser ->
                                    Log.e("REEL Added", "Yes button clicked")
                                    Log.e("REEL ADDED WITH LINK", "Reel link: $videoUrl")
var  caption = binding.caption.text.toString()
                                    if(caption==null)caption = " "
                                    var currentuserImage = currentUser.image
                                    if(currentuserImage==null)currentuserImage = " "
                                    Log.e("three thigs are","$caption $currentuserImage $videoUrl")

                                    val reel = Reel(videoUrl!!, caption, currentuserImage)
                                    Log.e("REEL DETAILS","THe reel details is $reel")
                                    Firebase.firestore.collection(REEL)
                                        .add(reel)
                                        .addOnSuccessListener { documentReference ->
                                            Log.e("Reel added", "Reel added with ID: ${documentReference.id}")
                                            startActivity(Intent(this, MainHomeActivity::class.java))
                                            finish()
                                        }
                                        .addOnFailureListener { e ->
                                            Log.e("Firestore", "Error adding reel", e)
                                        }
                                } ?: Log.e("Firestore", "User data not found")
                            }
                            .addOnFailureListener { e ->
                                Log.e("Firestore", "Error getting user data", e)
                            }
                    } ?: Log.e("Firebase Auth", "Current user not found")
                }

            }




        }

        binding.nobtn.setOnClickListener {
            startActivity(Intent(this, MainHomeActivity::class.java))
            finish()
        }



    }
}