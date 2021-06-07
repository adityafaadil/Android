package com.dicoding.capspro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.capspro.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
//            if (user != null) {
//                val mainActivityIntent = Intent(this, MainActivity::class.java)
//                startActivity(mainActivityIntent)
//                finish()
//            } else {
//                val signInIntent = Intent(this, LoginActivity::class.java)
//                startActivity(signInIntent)
//                finish()
//            }
        }, 4000)
    }
}