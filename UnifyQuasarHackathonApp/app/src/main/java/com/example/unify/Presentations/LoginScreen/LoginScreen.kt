package com.example.unify.Presentations.LoginScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.MainHomePage.MainHomeActivity
import com.example.unify.Presentations.SignUpScreen.SignUpScreen
import com.example.unify.R
import com.example.unify.databinding.ActivityLoginScreenBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginScreen : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoginScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginbtn.setOnClickListener{
            if(binding.username.text.toString().equals("") or
                binding.password.text.toString().equals("")){
                Toast.makeText(this,"Please Fill All the deatils",Toast.LENGTH_SHORT)
                    .show()            }
            else{
                var user = User(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.password!!)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(this,MainHomeActivity::class.java))
                        }
                        else{
                            Toast.makeText(this,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        binding.registerinstead.setOnClickListener {
            startActivity(Intent(this,SignUpScreen::class.java))
        }



    }
}