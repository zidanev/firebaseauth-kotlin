package com.example.anabul_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this use to hide the action bar
        supportActionBar?.hide()

        //use to put delay
        Handler(Looper.getMainLooper()).postDelayed({
            //this use to go to the next activity
            var intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            //this use to finish the current activity
            finish()
        }, 3000)
    }
}