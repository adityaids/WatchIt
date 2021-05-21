package com.aditya.watchit.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.watchit.R

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_IMAGE_TRANSITION: String = "extra_image_transition"
        const val EXTRA_DATA: String = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}