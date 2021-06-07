package com.dicoding.capspro.ui

import androidx.lifecycle.ViewModel
import com.dicoding.capspro.data.Repository

class PostingViewModel(private val repo: Repository) : ViewModel() {
    fun addReport(
        email: String,
        name: String,
        age: Int,
        gender: String,
        location: String,
        content: String
    ) = repo.addReport(email, name, age, gender, location, content)

    fun addThread(email: String, threadTitle: String, content: String) =
        repo.addThread(email, threadTitle, content)
}