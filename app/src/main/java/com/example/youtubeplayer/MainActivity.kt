package com.example.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlaysingle.setOnClickListener(this)
        btnStandAlone.setOnClickListener(this)
    }

    override fun onClick(v: View) {
       val intent =  when(v.id){
            R.id.btnPlaysingle -> Intent(this , YoutubeActivity::class.java)
            R.id.btnStandAlone -> Intent(this , StandaloneActivity::class.java)
           else -> throw IllegalArgumentException()
        }
        startActivity(intent)

    }
}

