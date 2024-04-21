package com.example.unify.PresentationsFaculty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.unify.R
import com.example.unify.Utils.POST_FOLDER
import com.example.unify.databinding.ActivityAttendanceBinding
import uploadImage

class AttendanceActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityAttendanceBinding.inflate(layoutInflater)
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

        binding.AddImage.setOnClickListener {
            launcher.launch("image/*")

        }
binding.MarkAttendance.setOnClickListener {

}

        setContentView(binding.root)
    }
}