package com.dicoding.capspro.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capspro.databinding.ActivityPostThreadBinding
import com.dicoding.capspro.utils.Constants.Companion.TEST_USER_EMAIL
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostThread : AppCompatActivity() {
    private lateinit var binding: ActivityPostThreadBinding
    private val viewModel: PostingViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postThreadSubmit.setOnClickListener {
            val threadTitle = binding.postThreadJudul.editText!!.text.toString()
            val content = binding.postThreadContent.editText!!.text.toString()
            viewModel.addThread(TEST_USER_EMAIL, threadTitle, content)
        }
    }
}