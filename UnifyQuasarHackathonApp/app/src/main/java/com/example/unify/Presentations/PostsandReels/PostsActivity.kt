package com.example.unify.Presentations.PostsandReels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.result.contract.ActivityResultContracts
import com.example.unify.Data.Models.Post
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.MainHomePage.MainHomeActivity
import com.example.unify.R
import com.example.unify.Utils.POST
import com.example.unify.Utils.POST_FOLDER
import com.example.unify.Utils.USER_NODE
import com.example.unify.Utils.USER_PROFILE_FOLDER
import com.example.unify.databinding.ActivityPostsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import uploadImage

class PostsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostsBinding.inflate(layoutInflater)
    }
    var imageUrl: String?=null

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri.let {
            if (uri != null) {
                uploadImage(uri, POST_FOLDER){
                    url->
                    if(it!=null){
        binding.AddImage.setImageURI(uri)
                        imageUrl= url
                    }
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
binding.materialToolbar.setNavigationOnClickListener {
    finish()
}
binding.AddImage.setOnClickListener {
    launcher.launch("image/*")

}
        binding.yesbtn.setOnClickListener {

Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
    var user = it.toObject<User>()
    Log.e("Postadded","yes btn clicked")
    val post : Post = Post(imageUrl!!,
        binding.caption.text.toString(),
        System.currentTimeMillis().toString(),
        it.id.toString()


        )
    Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
        Log.e("Postadded","post added${post} ")
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post)
            .addOnSuccessListener {
                startActivity(Intent(this,MainHomeActivity::class.java))
                finish()
            }


    }

}
        }

        binding.nobtn.setOnClickListener {
            startActivity(Intent(this,MainHomeActivity::class.java))
            finish()
        }


    }
}