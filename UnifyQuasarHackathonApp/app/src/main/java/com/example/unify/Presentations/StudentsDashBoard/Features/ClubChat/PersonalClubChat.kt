package com.example.unify.Presentations.StudentsDashBoard.Features.ClubChat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unify.Data.Models.User
import com.example.unify.Presentations.ChattingSystem.ChatAdapters.PersonalChatAdapter
import com.example.unify.Presentations.StudentsDashBoard.Features.ClubChat.Adapter.ClubChatsAdapter
import com.example.unify.R
import com.example.unify.Utils.USER_NODE
import com.example.unify.databinding.ActivityPersonalChatScreenBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class PersonalClubChat : AppCompatActivity() {
    val binding by lazy{
        ActivityPersonalChatScreenBinding.inflate(layoutInflater)
    }
    private lateinit var adapter : ClubChatsAdapter
    private  lateinit var messageList : ArrayList<com.example.unify.Data.Models.Message>
    var receiverRoom :String?= null
    var senderRoom :String?= null
    private lateinit var DBref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.materialToolbar3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar3.setNavigationOnClickListener {

            finish()
        }
        if (actionBar != null) {
            binding.materialToolbar3.setTitle("Test Name")
        }



        DBref = FirebaseDatabase.getInstance().getReference()

        val name = intent.getStringExtra("name")
        supportActionBar?.title = name
        messageList = ArrayList()
        adapter = ClubChatsAdapter(this,messageList)
        binding.chatsrv.layoutManager = LinearLayoutManager(this)
        binding.chatsrv.adapter = adapter



        val senderUid = FirebaseAuth.getInstance().currentUser!!.uid
        var personname = ""

        Firebase.firestore.collection(USER_NODE).document(senderUid).get()
            .addOnSuccessListener {
                val user: User = it.toObject<User>()!!
                personname = user.name.toString()
                Log.e("))))))","FIRST")

            }


        senderRoom = name + senderUid
        receiverRoom = name
        //Addchats to adapter

        try{
            DBref.child("chats").child(receiverRoom!!).child("messages")
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        Log.e("Personalized chats ","Came here moye")
                        messageList.clear()
                        for(postSnapshot in snapshot.children){
                            val message = postSnapshot.getValue(com.example.unify.Data.Models.Message::class.java)
                            messageList.add(message!!)
                        }
                        adapter.notifyDataSetChanged()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("Perosnal Chats ","the error is ${error.message}")                    }


                })

        }

        catch (e:Exception){

            Log.e("Personal chats ","NOT WORKING OMYE MOYE ${e.localizedMessage}")
        }


        binding.sendbtn.setOnClickListener {
            val message = binding.messagebox.text.toString()
            val messageObject = com.example.unify.Data.Models.Message(message, senderUid)
            DBref.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    DBref.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            binding.messagebox.setText("")

        }





        setContentView(binding.root)
    }
}