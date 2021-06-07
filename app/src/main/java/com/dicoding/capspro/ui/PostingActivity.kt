package com.dicoding.capspro.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capspro.databinding.ActivityPostingBinding

class PostingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postingReport.setOnClickListener {
            val intent = Intent(this, PostReport::class.java)
            startActivity(intent)
        }

        binding.postingThread.setOnClickListener {
            val intent = Intent(this, PostThread::class.java)
            startActivity(intent)
        }
    }
}