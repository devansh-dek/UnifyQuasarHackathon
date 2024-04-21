package com.example.unify.Presentations.SignUpScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.LoginScreen.LoginScreen
import com.example.unify.Presentations.MainHomePage.MainHomeActivity
import com.example.unify.Utils.USER_NODE
import com.example.unify.Utils.USER_PROFILE_FOLDER

import com.example.unify.databinding.ActivitySignUpScreenBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso
import uploadImage

class SignUpScreen : AppCompatActivity() {
    val binding by lazy {
        ActivitySignUpScreenBinding.inflate(layoutInflater)
    }
    lateinit var  user : User
    private lateinit var DBref: DatabaseReference
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri.let {
            if (uri != null) {
                uploadImage(uri, USER_PROFILE_FOLDER){
                    if(it==null){

                    } else{
                        user.image = it
                        binding.profileImage.setImageURI(uri)
                    }
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = User()
        if(intent.hasExtra("MODE")){
            if(intent.getIntExtra("MODE",-1)==1){
                binding.signUpBtn.text = "EDIT PROFILE BUTTON"
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                    .addOnSuccessListener {
                         user= it.toObject<User>()!!
                         if(!user.image.isNullOrEmpty()){
                            Picasso.get().load(user.image).into(binding.profileImage)
                        }
                        binding.password.setText(user.password)
                        binding.username.setText(user.name)
                        binding.useremail.setText(user.email)

                    }


            }
        }


        binding.signUpBtn.setOnClickListener {
            if(intent.hasExtra("MODE")){
                if(intent.getIntExtra("MODE",-1)==1){
                    Firebase.firestore.collection("User")
                        .document(Firebase.auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            startActivity(Intent(this@SignUpScreen,MainHomeActivity::class.java))
                            finish()
                            Toast.makeText(this,"User Details sent to firestore",Toast.LENGTH_SHORT).show()
                        }
                }
            }



            if(binding.username.text.toString().equals("") or
                binding.useremail.text.toString().equals("") or
                binding.password.text.toString().equals("")){
                Toast.makeText(this,"Please Fill Up All Details",Toast.LENGTH_SHORT).show()
            }
            else{

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.useremail.text.toString(),
                        binding.password.text.toString()
                    ).addOnSuccessListener {
                        user.email = binding.useremail.text.toString()
                        user.password =binding.password.text.toString()
                        user.name = binding.username.text.toString()
                        user!!.name?.let { it1 -> user!!.email?.let { it2 ->
                            addUserToRealTimeDatabase(it1,
                                it2,FirebaseAuth.getInstance().currentUser!!.uid)
                        } }
                        Firebase.firestore.collection("User")
                            .document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                startActivity(Intent(this@SignUpScreen,MainHomeActivity::class.java))
                                finish()
                                Toast.makeText(this,"User Details sent to firestore",Toast.LENGTH_SHORT).show()
                            }


                        Toast.makeText(this,"User Signedup Successfully",Toast.LENGTH_SHORT).show()

                    }
                        .addOnFailureListener {
                            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                        }
            }
        }
        binding.LoginScreenbtn.setOnClickListener {
            startActivity(Intent(this,LoginScreen::class.java))
        }
binding.profileImage.setOnClickListener {
    launcher.launch("image/*")
}

    }
    fun addUserToRealTimeDatabase(name: String,email: String,uid: String){
        DBref = FirebaseDatabase.getInstance().getReference()
        DBref.child("user").child(uid).setValue(User(name,email,uid))
    }
}